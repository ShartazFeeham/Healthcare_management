package com.healtcare.appointments.services.implementations;

import com.healtcare.appointments.entities.Schedule;
import com.healtcare.appointments.exception.AccessDeniedException;
import com.healtcare.appointments.exception.ItemNotFoundException;
import com.healtcare.appointments.models.AvailabilityDTO;
import com.healtcare.appointments.models.ScheduleGetDTO;
import com.healtcare.appointments.models.ScheduleSetDTO;
import com.healtcare.appointments.repositories.ScheduleRepository;
import com.healtcare.appointments.services.interfaces.AppointmentService;
import com.healtcare.appointments.services.interfaces.DelayService;
import com.healtcare.appointments.services.interfaces.ScheduleService;
import com.healtcare.appointments.utilities.TimeFormatter;
import com.healtcare.appointments.utilities.constants.AppointmentConstants;
import com.healtcare.appointments.utilities.token.IDExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final TimeFormatter timeFormatter;
    private final AppointmentService appointmentService;
    private final DelayService delayService;

    @Override
    public void setSchedule(ScheduleSetDTO scheduleDTO) throws AccessDeniedException {
        LocalDate currentDate = LocalDate.now();
        LocalDate scheduleDate = scheduleDTO.getDate();

        if (scheduleDate.isBefore(currentDate)) {
            throw new AccessDeniedException("You are not allowed to create a schedule for a date that is already passed!");
        }

        if (scheduleDate.isAfter(currentDate.plusDays(AppointmentConstants.ADVANCE_SCHEDULING_DAYS_LIMIT))) {
            throw new AccessDeniedException("You can only schedule for at most " + AppointmentConstants.ADVANCE_SCHEDULING_DAYS_LIMIT
                    + " upcoming days. " + timeFormatter.formatDate(scheduleDate) + " is "
                    + (Math.abs(ChronoUnit.DAYS.between(scheduleDate, currentDate))) + " days later.");
        }

        Optional<Schedule> scheduleOp = scheduleRepository.findByDateAndDoctorId(scheduleDate, IDExtractor.getUserID());
        if (scheduleOp.isEmpty()) {
            createSchedule(scheduleDTO);
        } else {
            updateSchedule(scheduleDTO, scheduleOp.get());
        }
    }

    private void createSchedule(ScheduleSetDTO scheduleDTO) throws AccessDeniedException {
        Schedule schedule = scheduleSetDtoToSchedule(scheduleDTO);
        scheduleRepository.save(schedule);
    }

    private void updateSchedule(ScheduleSetDTO scheduleDTO, Schedule schedule) throws AccessDeniedException {
        String doctorId = IDExtractor.getUserID();
        LocalDate date = scheduleDTO.getDate();

        schedule.setMorningShift(updateShiftStatusChanges(doctorId, date, AppointmentConstants.SHIFT1, scheduleDTO.getMorning(), schedule.getMorningShift()));
        schedule.setMorningCapacity(updateShiftCapacityChanges(doctorId, date, AppointmentConstants.SHIFT1, scheduleDTO.getMorningCapacity(), schedule.getMorningCapacity()));

        schedule.setAfternoonShift(updateShiftStatusChanges(doctorId, date, AppointmentConstants.SHIFT2, scheduleDTO.getAfterNoon(), schedule.getAfternoonShift()));
        schedule.setAfternoonCapacity(updateShiftCapacityChanges(doctorId, date, AppointmentConstants.SHIFT2, scheduleDTO.getAfterNoonCapacity(), schedule.getAfternoonCapacity()));

        schedule.setEveningShift(updateShiftStatusChanges(doctorId, date, AppointmentConstants.SHIFT3, scheduleDTO.getEvening(), schedule.getEveningShift()));
        schedule.setEveningCapacity(updateShiftCapacityChanges(doctorId, date, AppointmentConstants.SHIFT3, scheduleDTO.getEveningCapacity(), schedule.getEveningCapacity()));

        scheduleRepository.save(schedule);
    }

    private Integer updateShiftStatusChanges(String doctorId, LocalDate date, String slotName, Integer shiftChange, Integer shiftExisting)
            throws AccessDeniedException {
        if (!shiftChange.equals(shiftExisting)) {
            if (appointmentService.countBookedAppointments(doctorId, date, slotName) > 0) {
                throw new AccessDeniedException("Patients have already booked appointments in " + slotName + " slot of "
                        + timeFormatter.formatDate(date) + ", you can't change it anymore");
            }
        }
        return shiftChange;
    }

    private Integer updateShiftCapacityChanges(String doctorId, LocalDate date, String slotName, Integer capacityChange, Integer capacityExisting)
            throws AccessDeniedException {
        if (!capacityChange.equals(capacityExisting)) {
            Integer bookings = appointmentService.countAppointmentCapacity(doctorId, date, slotName);
            if (bookings > capacityChange) {
                throw new AccessDeniedException("Patients have already booked " + bookings + " appointments in " + slotName + " slot of "
                        + timeFormatter.formatDate(date) + ", you can't change it below " + bookings);
            }
        }
        return capacityChange;
    }

    @Override
    public ScheduleGetDTO getScheduleByDateAndDoctorId(String date, String doctorId) {
        LocalDate parsedDate = LocalDate.parse(date);
        Optional<Schedule> schedule = scheduleRepository.findByDateAndDoctorId(parsedDate, doctorId);

        if (schedule.isEmpty()) {
            throw new ItemNotFoundException("schedule in date " + date + " for doctor", doctorId);
        }

        return scheduleToScheduleGetDto(schedule.get());
    }

    @Override
    public List<LocalDate> getDatesByDoctorId(String doctorId) {
        return scheduleRepository.findDistinctDatesByDoctorIdAndStartDate(doctorId, LocalDate.now().minusDays(1));
    }

    private Schedule scheduleSetDtoToSchedule(ScheduleSetDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setDoctorId(IDExtractor.getUserID());
        schedule.setDate(scheduleDTO.getDate());
        schedule.setMorningShift(scheduleDTO.getMorning());
        schedule.setAfternoonShift(scheduleDTO.getAfterNoon());
        schedule.setEveningShift(scheduleDTO.getEvening());
        schedule.setMorningCapacity(scheduleDTO.getMorningCapacity());
        schedule.setAfternoonCapacity(scheduleDTO.getAfterNoonCapacity());
        schedule.setEveningCapacity(scheduleDTO.getEveningCapacity());
        return schedule;
    }

    private ScheduleGetDTO scheduleToScheduleGetDto(Schedule schedule) {
        LocalDate appointmentDate = schedule.getDate();
        // Morning
        LocalDateTime morningEndTime = appointmentDate.atTime(AppointmentConstants.MORNING_END_TIME.minusMinutes(1));
        Integer morningBookings = appointmentService.countAppointmentCapacity(schedule.getDoctorId(), appointmentDate, AppointmentConstants.SHIFT1);
        Integer morningDelay = delayService.getDelayInMinutes(schedule.getDoctorId(), AppointmentConstants.SHIFT1, morningEndTime);
        // Afternoon
        LocalDateTime afternoonEndTime = appointmentDate.atTime(AppointmentConstants.AFTERNOON_END_TIME.minusMinutes(1));
        Integer afternoonBookings = appointmentService.countAppointmentCapacity(schedule.getDoctorId(), appointmentDate, AppointmentConstants.SHIFT2);
        Integer afternoonDelay = delayService.getDelayInMinutes(schedule.getDoctorId(), AppointmentConstants.SHIFT2, afternoonEndTime);
        // Evening
        LocalDateTime eveningEndTime = appointmentDate.atTime(AppointmentConstants.EVENING_END_TIME.minusMinutes(1));
        Integer eveningBookings = appointmentService.countAppointmentCapacity(schedule.getDoctorId(), appointmentDate, AppointmentConstants.SHIFT3);
        Integer eveningDelay = delayService.getDelayInMinutes(schedule.getDoctorId(), AppointmentConstants.SHIFT3, eveningEndTime);

        ScheduleGetDTO scheduleGetDTO = new ScheduleGetDTO();
        scheduleGetDTO.setMorning(schedule.getMorningShift());
        scheduleGetDTO.setMorningAvailability(new AvailabilityDTO(schedule.getMorningCapacity(), morningBookings, morningDelay));
        scheduleGetDTO.setAfterNoon(schedule.getAfternoonShift());
        scheduleGetDTO.setAfternoonAvailability(new AvailabilityDTO(schedule.getAfternoonCapacity(), afternoonBookings, afternoonDelay));
        scheduleGetDTO.setEvening(schedule.getEveningShift());
        scheduleGetDTO.setEveningAvailability(new AvailabilityDTO(schedule.getEveningCapacity(), eveningBookings, eveningDelay));

        return scheduleGetDTO;
    }
}

package com.healtcare.appointments.services.implementations;

import com.healtcare.appointments.exception.AccessDeniedException;
import com.healtcare.appointments.exception.ItemNotFoundException;
import com.healtcare.appointments.entities.Schedule;
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

    // Method to set a schedule
    @Override
    public void setSchedule(ScheduleSetDTO scheduleDTO) throws AccessDeniedException {
        LocalDate currentDate = LocalDate.now();
        LocalDate scheduleDate = scheduleDTO.getDate();

        // Check if the schedule date is in the past
        if (scheduleDate.isBefore(currentDate)) {
            throw new AccessDeniedException("You are not allowed to create a schedule for a date that is already passed!");
        }

        // Check if the schedule date is within the allowed scheduling limit
        if (scheduleDate.isAfter(currentDate.plusDays(AppointmentConstants.ADVANCE_SCHEDULING_DAYS_LIMIT))) {
            throw new AccessDeniedException("You can only schedule for at most " + AppointmentConstants.ADVANCE_SCHEDULING_DAYS_LIMIT
                    + " upcoming days. " + timeFormatter.formatDate(scheduleDate) + " is "
                    + (Math.abs(ChronoUnit.DAYS.between(scheduleDate, currentDate))) + " days later.");
        }

        Optional<Schedule> scheduleOp = scheduleRepository.findByDateAndDoctorId(scheduleDate, IDExtractor.getUserID());
        checkCapacityCap(scheduleDTO);

        // If the schedule doesn't exist, create a new one; otherwise, update the existing schedule
        if (scheduleOp.isEmpty()) {
            createSchedule(scheduleDTO);
        } else {
            updateSchedule(scheduleDTO, scheduleOp.get());
        }
    }

    // Method to check if the capacity is within the allowed limits
    private void checkCapacityCap(ScheduleSetDTO scheduleDTO){
        if(scheduleDTO.getMorningCapacity() < AppointmentConstants.MIN_CAPACITY_LIMIT
                || scheduleDTO.getMorningCapacity() > AppointmentConstants.MAX_CAPACITY_LIMIT){
            throw new AccessDeniedException("The allowed number of capacity per slot is " + AppointmentConstants.MIN_CAPACITY_LIMIT
                    +" to " + AppointmentConstants.MAX_CAPACITY_LIMIT + ". You attempted " + scheduleDTO.getMorningCapacity());
        }
        if(scheduleDTO.getAfterNoonCapacity() < AppointmentConstants.MIN_CAPACITY_LIMIT
                || scheduleDTO.getAfterNoonCapacity() > AppointmentConstants.MAX_CAPACITY_LIMIT){
            throw new AccessDeniedException("The allowed number of capacity per slot is " + AppointmentConstants.MIN_CAPACITY_LIMIT
                    +" to " + AppointmentConstants.MAX_CAPACITY_LIMIT + ". You attempted " + scheduleDTO.getAfterNoonCapacity());
        }
        if(scheduleDTO.getEveningCapacity() < AppointmentConstants.MIN_CAPACITY_LIMIT
                || scheduleDTO.getEveningCapacity() > AppointmentConstants.MAX_CAPACITY_LIMIT){
            throw new AccessDeniedException("The allowed number of capacity per slot is " + AppointmentConstants.MIN_CAPACITY_LIMIT
                    +" to " + AppointmentConstants.MAX_CAPACITY_LIMIT + ". You attempted " + scheduleDTO.getEveningCapacity());
        }
    }

    // Method to create a new schedule
    private void createSchedule(ScheduleSetDTO scheduleDTO) throws AccessDeniedException {
        Schedule schedule = scheduleSetDtoToSchedule(scheduleDTO);
        scheduleRepository.save(schedule);
    }

    // Method to update an existing schedule
    private void updateSchedule(ScheduleSetDTO scheduleDTO, Schedule schedule) throws AccessDeniedException {
        String doctorId = IDExtractor.getUserID();
        LocalDate date = scheduleDTO.getDate();

        // Update morning shift and capacity
        schedule.setMorningShift(updateShiftStatusChanges(doctorId, date, AppointmentConstants.SHIFT1, scheduleDTO.getMorning(), schedule.getMorningShift()));
        schedule.setMorningCapacity(updateShiftCapacityChanges(doctorId, date, AppointmentConstants.SHIFT1, scheduleDTO.getMorningCapacity(), schedule.getMorningCapacity()));

        // Update afternoon shift and capacity
        schedule.setAfternoonShift(updateShiftStatusChanges(doctorId, date, AppointmentConstants.SHIFT2, scheduleDTO.getAfterNoon(), schedule.getAfternoonShift()));
        schedule.setAfternoonCapacity(updateShiftCapacityChanges(doctorId, date, AppointmentConstants.SHIFT2, scheduleDTO.getAfterNoonCapacity(), schedule.getAfternoonCapacity()));

        // Update evening shift and capacity
        schedule.setEveningShift(updateShiftStatusChanges(doctorId, date, AppointmentConstants.SHIFT3, scheduleDTO.getEvening(), schedule.getEveningShift()));
        schedule.setEveningCapacity(updateShiftCapacityChanges(doctorId, date, AppointmentConstants.SHIFT3, scheduleDTO.getEveningCapacity(), schedule.getEveningCapacity()));

        scheduleRepository.save(schedule);
    }

    // Method to update shift status changes
    private Integer updateShiftStatusChanges(String doctorId, LocalDate date, String slotName, Integer shiftChange, Integer shiftExisting)
            throws AccessDeniedException {
        if (!shiftChange.equals(shiftExisting)) {
            // Check if there are booked appointments in the slot
            if (appointmentService.countBookedAppointments(doctorId, date, slotName) > 0) {
                throw new AccessDeniedException("Patients have already booked appointments in " + slotName + " slot of "
                        + timeFormatter.formatDate(date) + ", you can't change it anymore");
            }
        }
        return shiftChange;
    }

    // Method to update shift capacity changes
    private Integer updateShiftCapacityChanges(String doctorId, LocalDate date, String slotName, Integer capacityChange, Integer capacityExisting)
            throws AccessDeniedException {
        if (!capacityChange.equals(capacityExisting)) {
            Integer bookings = appointmentService.countAppointmentCapacity(doctorId, date, slotName);
            // Check if there are booked appointments exceeding the new capacity
            if (bookings > capacityChange) {
                throw new AccessDeniedException("Patients have already booked " + bookings + " appointments in " + slotName + " slot of "
                        + timeFormatter.formatDate(date) + ", you can't change it below " + bookings);
            }
        }
        return capacityChange;
    }

    // Method to get a schedule by date and doctor ID
    @Override
    public ScheduleGetDTO getScheduleByDateAndDoctorId(String date, String doctorId) {
        LocalDate parsedDate = LocalDate.parse(date);
        Optional<Schedule> schedule = scheduleRepository.findByDateAndDoctorId(parsedDate, doctorId);

        if (schedule.isEmpty()) {
            throw new ItemNotFoundException("schedule in date " + date + " for doctor", doctorId);
        }

        return scheduleToScheduleGetDto(schedule.get());
    }

    // Method to get dates by doctor ID
    @Override
    public List<LocalDate> getDatesByDoctorId(String doctorId) {
        return scheduleRepository.findDistinctDatesByDoctorIdAndStartDate(doctorId, LocalDate.now().minusDays(1));
    }

    // Method to convert ScheduleSetDTO to Schedule
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

    // Method to convert Schedule to ScheduleGetDTO
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

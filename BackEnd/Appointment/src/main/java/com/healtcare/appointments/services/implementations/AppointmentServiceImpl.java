package com.healtcare.appointments.services.implementations;

import com.healtcare.appointments.entities.Appointment;
import com.healtcare.appointments.entities.Schedule;
import com.healtcare.appointments.exception.AccessDeniedException;
import com.healtcare.appointments.exception.ItemNotFoundException;
import com.healtcare.appointments.models.AppointmentListDTO;
import com.healtcare.appointments.models.AppointmentRequestDTO;
import com.healtcare.appointments.repositories.AppointmentRepository;
import com.healtcare.appointments.repositories.ScheduleRepository;
import com.healtcare.appointments.services.interfaces.AppointmentService;
import com.healtcare.appointments.services.interfaces.DelayService;
import com.healtcare.appointments.utilities.TimeFormatter;
import com.healtcare.appointments.utilities.constants.AppointmentConstants;
import com.healtcare.appointments.utilities.token.IDExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service @RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ScheduleRepository scheduleRepository;
    private final TimeFormatter timeFormatter;
    private final DelayService delayService;

    @Override
    public Appointment createAppointment(AppointmentRequestDTO appReq) {
        // List down the existing appointments by the expected doctor in the expected date and shift.
        List<Appointment> appointments = appointmentRepository.findByDoctorIdAndDateAndShift(
                appReq.getDoctorId(), appReq.getDate(), shiftNoToShift(appReq.getShift()));

        // Find out the schedule (day) requested by patient
        Optional<Schedule> scheduleOp = scheduleRepository.findByDateAndDoctorId(appReq.getDate(), appReq.getDoctorId());
        if(scheduleOp.isEmpty()) throw new ItemNotFoundException("schedule date " + timeFormatter.formatDate(appReq.getDate()) + " and doctor", appReq.getDoctorId());

        // Get the schedule set by doctor.
        Schedule schedule = scheduleOp.get();
        // Retrieve capacity of the requested dates requested slot.
        Integer capacity = getCapacity(appReq.getShift(), schedule);
        // Get the type of the appointment based on slot-type(in-person/telemedicine/NA) from the schedule set by doctor
        String type = getType(schedule, appReq.getShift());
        // Admins can only create appointment for un-registered user, as an unregistered user can not join video call
        // so, they can only get in-person appointment, as a result, admins should be prohibited for telemedicine requests
        if(IDExtractor.getUserID().startsWith("A") && type.equals(AppointmentConstants.TYPE3)){
            throw new AccessDeniedException("As an admin you can only request in-person appointments for un-registered users.");
        }
        // Find the first available slot from the appointments list.
        // Filter appointments based on cancellation status
        List<Integer> activeSerials = appointments.stream().filter(appointment -> !appointment.isCancelled()).map(Appointment::getSerialNo).toList();
        // Passing that list in a method and attempting changes on it will result in Immutable collection exception so need a new list.
        List<Integer> activeSerialsCopy = new ArrayList<>(activeSerials);
        // Get a correctly calculated serial that is not in the time already past.
        int availableSerial = getCalculatedSerial(appReq, activeSerialsCopy, capacity);
        LocalDateTime appointmentTime = getAppointmentTime(appReq.getShift(), appReq.getDate(), availableSerial, capacity);
        // Create appointment, save and return
        String appointmentId = generateAppointmentId(IDExtractor.getUserID(), appReq.getDoctorId());
        Appointment appointment = new Appointment(appointmentId,
                appReq.getDoctorId(), IDExtractor.getUserID().startsWith("A") ?
                appointmentId.substring(appointmentId.indexOf("-") + 1) : IDExtractor.getUserID(),
                appReq.getDate(), shiftNoToShift(appReq.getShift()), type, availableSerial,
                appointmentTime, LocalDateTime.now(), false);

        appointmentRepository.save(appointment);
        return appointment;
    }

    // If someone asks an appointment while the slot is running (not over yet) and there are available early seals in that slot
    // then the system may assign appointment with time that is already passed,
    // Here is an example response
    // "appointmentTime": "2023-11-22T08:48:00",
    // "schedulingTime": "2023-11-22T10:09:52.2466131",
    // to address this issue, the following method populates the serial list until the assigned serial is in correct time.
    private int getCalculatedSerial(AppointmentRequestDTO appReq, List<Integer> activeSerials, int capacity){
        // Find the first missing serial ID
        int availableSerial = IntStream.rangeClosed(1, activeSerials.size() + 1)
                .filter(serial -> !activeSerials.contains(serial)).findFirst().orElse(activeSerials.size() + 1);

        // If slot is full then there can be no appointments placed.
        if(availableSerial > capacity) throw new AccessDeniedException("The requested slot is already full, try another slot or date or doctor");

        // Get the appointment time based on serial no and validate the time, if okay then return.
        // But if the time is already passed then generate a new time with the next slot.
        LocalDateTime appointmentTime = getAppointmentTime(appReq.getShift(), appReq.getDate(), availableSerial, capacity);
        if(appointmentTime.isBefore(LocalDateTime.now())) {
            activeSerials.add(availableSerial);
            return getCalculatedSerial(appReq, activeSerials, capacity);
        }
        return availableSerial;
    }

    private String generateAppointmentId(String patientId, String docId){
        String idPattern = docId + "-" + patientId + "-";
        if(patientId.startsWith("A")){
            idPattern = docId + "-" + "ExP";
        }
        long count = appointmentRepository.countByIdStartingWith(idPattern) + 1;
        return  idPattern + count;
    }

    private String getType(Schedule schedule, int shift) {
        if (shiftNoToShift(shift).equals(AppointmentConstants.SHIFT1)) return intTypeToStringType(schedule.getMorningShift());
        if (shiftNoToShift(shift).equals(AppointmentConstants.SHIFT2)) return intTypeToStringType(schedule.getAfternoonShift());
        if (shiftNoToShift(shift).equals(AppointmentConstants.SHIFT3)) return intTypeToStringType(schedule.getEveningShift());
        throw new ItemNotFoundException("slot", shiftNoToShift(shift));
    }

    private String intTypeToStringType(int type){
        if(type == 0) throw new AccessDeniedException("Doctor is not available in requested time!");
        if(type == 1) return AppointmentConstants.TYPE2;
        if(type == 2) return AppointmentConstants.TYPE3;
        throw new ItemNotFoundException("type no", String.valueOf(type));
    }

    private Integer getCapacity(int shift, Schedule schedule){
        if(shiftNoToShift(shift).equals(AppointmentConstants.SHIFT1)) return schedule.getMorningCapacity();
        else if(shiftNoToShift(shift).equals(AppointmentConstants.SHIFT2)) return schedule.getAfternoonCapacity();
        else if(shiftNoToShift(shift).equals(AppointmentConstants.SHIFT3)) return schedule.getEveningCapacity();
        else throw new ItemNotFoundException("slot", shiftNoToShift(shift));
    }

    private LocalDateTime getAppointmentTime(int shift, LocalDate date, int availableSerial, int capacity){
        if (shiftNoToShift(shift).equals(AppointmentConstants.SHIFT1)) {
            return calculateAppointmentTime(date, AppointmentConstants.MORNING_START_TIME, availableSerial, capacity);
        } else if (shiftNoToShift(shift).equals(AppointmentConstants.SHIFT2)) {
            return calculateAppointmentTime(date, AppointmentConstants.AFTERNOON_START_TIME, availableSerial, capacity);
        } else if (shiftNoToShift(shift).equals(AppointmentConstants.SHIFT3)) {
            return calculateAppointmentTime(date, AppointmentConstants.EVENING_START_TIME, availableSerial, capacity);
        } else throw new ItemNotFoundException("slot", shiftNoToShift(shift));
    }

    private LocalDateTime calculateAppointmentTime(LocalDate date, LocalTime slotStartTime, Integer availableSerial, Integer capacity) {
        long minutesPerSlot = (4 * 60) / capacity;
        long startsAt = (availableSerial - 1) * minutesPerSlot;
        LocalDateTime baseTime = LocalDateTime.of(date, slotStartTime);
        return baseTime.plusMinutes(startsAt);
    }

    private String shiftNoToShift(Integer shiftNo){
        if(shiftNo == 1) return AppointmentConstants.SHIFT1;
        if(shiftNo == 2) return AppointmentConstants.SHIFT2;
        if(shiftNo == 3) return AppointmentConstants.SHIFT3;
        throw new ItemNotFoundException("slot", shiftNo.toString());
    }

    @Override
    public Appointment getAppointment(String appointmentId) {
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
        if(appointment.isEmpty()) throw new ItemNotFoundException("appointment", appointmentId);
        return appointment.get();
    }

    @Override
    public void cancelAppointment(String appointmentId) throws AccessDeniedException {
        Appointment appointment = getAppointment(appointmentId);

        Duration appointmentRemainingTime = Duration.between(LocalDateTime.now(), appointment.getAppointmentTime());
        Duration appointmentRequestTime = Duration.between(LocalDateTime.now(), appointment.getSchedulingTime());

        if (appointmentRemainingTime.toHours() >= AppointmentConstants.CANCEL_TIME_LIMIT_BEFORE_APPOINTMENT
        || appointmentRequestTime.toMinutes() >= AppointmentConstants.CANCEL_TIME_LIMIT_AFTER_REQUEST) {
            appointment.setCancelled(true);
            appointmentRepository.save(appointment);
        } else throw new AccessDeniedException("Appointment cannot be canceled. You can only cancel an appointment before "
                + AppointmentConstants.CANCEL_TIME_LIMIT_BEFORE_APPOINTMENT + " hours of the appointment or "
                + AppointmentConstants.CANCEL_TIME_LIMIT_AFTER_REQUEST + " minutes after placing it");
    }

    @Override
    public List<AppointmentListDTO> getCompleteAppointmentsByPatient(String patientId) {
        List<Appointment> appointments = appointmentRepository.findByPatientIdAndCancelled(patientId, false);
        return appointmentListToAppointmentListRead(appointments.stream()
                .filter(this::isAppointmentComplete).toList());
    }

    @Override
    public List<AppointmentListDTO> getCompleteAppointmentsByDoctor(String doctorId) {
        List<Appointment> appointments = appointmentRepository.findByDoctorIdAndCancelled(doctorId, false);
        return appointmentListToAppointmentListRead(appointments.stream()
                .filter(this::isAppointmentComplete).toList());
    }

    // Helper method to check if the appointment is before (now + delay + 20 minutes)
    private boolean isAppointmentComplete(Appointment appointment) {
        return getDelayedAdjustedTIme(appointment).plusMinutes(20).isBefore(LocalDateTime.now());
    }

    private LocalDateTime getDelayedAdjustedTIme(Appointment appointment){
        int delayTime = delayService.getDelayInMinutes(appointment.getDoctorId(), appointment.getShift(), appointment.getAppointmentTime());
        return appointment.getAppointmentTime().plusMinutes(delayTime);
    }

    @Override
    public List<AppointmentListDTO> getUpcomingAppointmentsByPatient(String patientId) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<Appointment> appointments = appointmentRepository.findByPatientIdAndCancelledAndAppointmentTimeAfter(patientId, false, currentDateTime);
        return appointmentListToAppointmentListRead(appointments);
    }

    @Override
    public List<AppointmentListDTO> getUpcomingAppointmentsByDoctor(String doctorId) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<Appointment> appointments = appointmentRepository.findByDoctorIdAndCancelledAndAppointmentTimeAfter(doctorId, false, currentDateTime);
        return appointmentListToAppointmentListRead(appointments);
    }

    private List<AppointmentListDTO> appointmentListToAppointmentListRead(List<Appointment> appointments){
        return appointments.stream()
                .sorted(Comparator.comparing(Appointment::getAppointmentTime))
                .map(appointment -> new AppointmentListDTO(appointment.getId(),
                        timeFormatter.getReadableDateTime(getDelayedAdjustedTIme(appointment)),
                        appointment.getPatientId(), appointment.getDoctorId(), appointment.getType()))
                .collect(Collectors.toList());
    }

    @Override
    public Integer countBookedAppointments(String doctorId, LocalDate date, String shift) {
        return appointmentRepository.countByDoctorIdAndDateAndShift(doctorId, date, shift);
    }

    @Override
    // Count the available appointment capacity for the doctor on the specified date and shift
    public Integer countAppointmentCapacity(String doctorId, LocalDate date, String shift) {
        return appointmentRepository.countByDoctorIdAndDateAndShiftAndCancelled(doctorId, date, shift, false);
    }

    @Override
    // Count the total number of appointments for the doctor
    public Integer countTotalAppointmentByDoctorId(String doctorId) {
        return appointmentRepository.countByDoctorId(doctorId);
    }

    @Override
    // Check if an appointment with the given ID exists
    public boolean checkAppointmentValidity(String appointmentId) {
        return appointmentRepository.existsById(appointmentId);
    }
}

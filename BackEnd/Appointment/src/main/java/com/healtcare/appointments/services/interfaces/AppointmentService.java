package com.healtcare.appointments.services.interfaces;

import com.healtcare.appointments.entities.Appointment;
import com.healtcare.appointments.models.AppointmentListDTO;
import com.healtcare.appointments.models.AppointmentRequestDTO;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AppointmentService {
    public Appointment createAppointment(AppointmentRequestDTO appointmentRequestDTO);
    public Appointment getAppointment(String appointmentId);
    public void cancelAppointment(String appointmentId) throws AccessDeniedException;
    public List<AppointmentListDTO> getCompleteAppointmentsByPatient(String patientId);
    public List<AppointmentListDTO> getUpcomingAppointmentsByPatient(String patientId);
    public List<AppointmentListDTO> getPublicCompletedAppointments();
    public List<AppointmentListDTO> getPublicUpcomingAppointments();
    public List<AppointmentListDTO> getUpcomingAppointmentsByDoctor(String doctorId);
    public Integer countBookedAppointments(String doctorId, LocalDate date, String shift);
    public Integer countAppointmentCapacity(String doctorId, LocalDate date, String shift);
    public Integer countTotalAppointmentByDoctorId(String doctorId);
    public boolean checkAppointmentValidity(String appointmentId);
    List<AppointmentListDTO> getCompleteAppointmentsByDoctor(String doctorId);
    Long totalCount();
    Map<String, Object> getAppointmentStatistics();
}
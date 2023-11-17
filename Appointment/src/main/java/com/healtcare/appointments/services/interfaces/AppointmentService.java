package com.healtcare.appointments.services.interfaces;

import com.healtcare.appointments.entities.Appointment;
import com.healtcare.appointments.models.AppointmentListDTO;
import com.healtcare.appointments.models.AppointmentRequestDTO;
import java.util.List;

public interface AppointmentService {
    public Appointment createAppointment(AppointmentRequestDTO appointmentRequestDTO);
    public Appointment getAppointment(String appointmentId);
    public void cancelAppointment(String appointmentId);
    public List<AppointmentListDTO> getCompleteAppointmentsByPatient(String patientId);
    public List<AppointmentListDTO> getUpcomingAppointmentsByPatient(String patientId);
    public Integer countAppointments(String doctorId, String date, String shift);
    public Integer countAppointmentCapacity(String doctorId, String date, String shift);
    public Integer countTotalAppointmentByDoctorId(String doctorId);
    public boolean checkAppointmentValidity(String appointmentId);
}
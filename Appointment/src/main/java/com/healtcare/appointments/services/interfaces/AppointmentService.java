package com.healtcare.appointments.services.interfaces;

import com.healtcare.appointments.entities.Appointment;
import com.healtcare.appointments.models.AppointmentListDTO;
import com.healtcare.appointments.models.AppointmentRequestDTO;
import com.healtcare.appointments.models.AvailabilityDTO;

import java.time.LocalDate;

public interface AppointmentService {
    public Appointment createAppointment(AppointmentRequestDTO appointmentRequestDTO);
    public Appointment getAppointment(String appointmentId);
    public void cancelAppointment(String appointmentId);
    public AppointmentListDTO getAppointmentsByPatientDone(String patientId);
    public AppointmentListDTO getAppointmentsByPatientUpcoming(String patientId);
    public Integer getAppointCountByDoctor(String doctorId);
    public boolean checkAppointmentValidity(String appointmentId);
}

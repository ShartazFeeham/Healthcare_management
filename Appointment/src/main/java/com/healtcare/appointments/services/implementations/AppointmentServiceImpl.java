package com.healtcare.appointments.services.implementations;

import com.healtcare.appointments.entities.Appointment;
import com.healtcare.appointments.models.AppointmentListDTO;
import com.healtcare.appointments.models.AppointmentRequestDTO;
import com.healtcare.appointments.services.interfaces.AppointmentService;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Override
    public Appointment createAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        return null;
    }

    @Override
    public Appointment getAppointment(String appointmentId) {
        return null;
    }

    @Override
    public void cancelAppointment(String appointmentId) {

    }

    @Override
    public AppointmentListDTO getAppointmentsByPatientDone(String patientId) {
        return null;
    }

    @Override
    public AppointmentListDTO getAppointmentsByPatientUpcoming(String patientId) {
        return null;
    }

    @Override
    public Integer getAppointCountByDoctor(String doctorId) {
        return null;
    }

    @Override
    public boolean checkAppointmentValidity(String appointmentId) {
        return false;
    }
}

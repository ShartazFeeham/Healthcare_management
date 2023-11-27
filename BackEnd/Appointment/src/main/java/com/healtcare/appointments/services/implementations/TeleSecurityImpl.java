package com.healtcare.appointments.services.implementations;

import com.healtcare.appointments.exception.AccessDeniedException;
import com.healtcare.appointments.entities.Appointment;
import com.healtcare.appointments.services.interfaces.AppointmentService;
import com.healtcare.appointments.services.interfaces.DelayService;
import com.healtcare.appointments.services.interfaces.TeleSecurityService;
import com.healtcare.appointments.utilities.token.IDExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TeleSecurityImpl implements TeleSecurityService {

    private final AppointmentService appointmentService;
    private final DelayService delayService;

    @Override
    public void verify(String appointmentId) {
        Appointment appointment = appointmentService.getAppointment(appointmentId);
        LocalDateTime now = LocalDateTime.now();
        Integer delayInMinutes = delayService.getDelayInMinutes(appointment.getDoctorId(), appointment.getShift(), appointment.getAppointmentTime());
        LocalDateTime appointmentTime = appointment.getAppointmentTime().plusMinutes(delayInMinutes);

        // Check whether the user has right to join the appointment or not.
        if(!appointmentId.contains(IDExtractor.getUserID())){
            throw new AccessDeniedException("You can not join someone else's appointment!");
        }

        // If attempt to joining time is within 5 minutes before or 20 minutes after appointment time, then allow.
        // In reverse, if joining time is before 5 minutes or after 20 minutes of appointment time, then prohibit.
        if (appointmentTime.isBefore(now.minusMinutes(5)) || appointmentTime.isAfter(now.plusMinutes(20))) {
            throw new AccessDeniedException("You can only join within 5 minutes before or a few minutes after the appointment time starts.");
        }
    }
}

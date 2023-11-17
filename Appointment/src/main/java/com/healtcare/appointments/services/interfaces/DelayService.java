package com.healtcare.appointments.services.interfaces;

import com.healtcare.appointments.entities.Delay;

import java.time.LocalDateTime;

public interface DelayService {
    public void updateDelay(Integer minutes);
    public Integer getDelayInMinutes(String doctorId, String shift, LocalDateTime appointmentTime);
}

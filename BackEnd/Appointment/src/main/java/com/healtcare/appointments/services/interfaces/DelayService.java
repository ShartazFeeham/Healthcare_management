package com.healtcare.appointments.services.interfaces;
import java.time.LocalDateTime;

public interface DelayService {
    public void updateDelay(Integer minutes);
    public Integer getDelayInMinutes(String doctorId, String shift, LocalDateTime appointmentTime);
    public Integer getCurrentDelayInMinutes(String doctorId);
}

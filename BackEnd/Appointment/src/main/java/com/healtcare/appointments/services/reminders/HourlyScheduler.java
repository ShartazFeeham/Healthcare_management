package com.healtcare.appointments.services.reminders;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component @AllArgsConstructor
public class HourlyScheduler {
    private final Remind remind;
    @Scheduled(fixedDelay = 1000 * 60 * 60, initialDelay = 1000)
    public void scheduleTask() {
        remind.sendNotifications(55);
    }
}

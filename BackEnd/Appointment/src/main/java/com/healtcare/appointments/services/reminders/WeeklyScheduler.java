package com.healtcare.appointments.services.reminders;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component @AllArgsConstructor
public class WeeklyScheduler {
    private final Remind remind;
    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24 * 7, initialDelay = 1000)
    public void scheduleTask() {
        remind.sendNotifications(null, 60 * 24 * 7);
    }
}

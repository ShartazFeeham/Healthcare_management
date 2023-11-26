package com.healtcare.appointments.services.reminders;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component @AllArgsConstructor
public class ShiftedScheduler {
    private final Remind remind;
    @Scheduled(fixedDelay = 1000 * 60 * 60 * 8, initialDelay = 1000)
    public void scheduleTask() {
        remind.sendNotifications(null, 60 * 8);
    }
}

package com.healtcare.appointments.utilities;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TimeFormatter {
    public String format(LocalDateTime localDateTime) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(localDateTime, now);

        if (duration.getSeconds() < 60) {
            return "Just now";
        } else if (duration.toMinutes() < 60) {
            return duration.toMinutes() + " minutes ago";
        } else if (duration.toHours() < 24) {
            return duration.toHours() + " hours ago";
        } else if (duration.toDays() <= 6) {
            return duration.toDays() + " days ago";
        } else if (duration.toDays() <= 28) {
            return (duration.toDays() / 7) + " weeks ago";
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mma");
            return localDateTime.format(formatter);
        }
    }
}

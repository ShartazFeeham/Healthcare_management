package com.healtcare.community.utilities;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class TimeFormatter {
    public String format(LocalDateTime localDateTime) {
        if(localDateTime.isAfter(LocalDateTime.now())) return getUpcomingTimeFormat(localDateTime);
        else return getPastTimeFormat(localDateTime);
    }

    private String getPastTimeFormat(LocalDateTime localDateTime) {
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
    private String getUpcomingTimeFormat(LocalDateTime localDateTime) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, localDateTime);

        if (duration.isZero()) {
            return "Just now";
        } else if (duration.toMinutes() < 1) {
            return "Any moment now";
        } else if (duration.toMinutes() < 60) {
            return duration.toMinutes() + " minutes later";
        } else if (duration.toHours() < 24) {
            return duration.toHours() + " hours later";
        } else if (duration.toDays() < 7) {
            return duration.toDays() + " days later";
        } else if (duration.toDays() < 28) {
            return (duration.toDays() / 7) + " weeks later";
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mma");
            return localDateTime.format(formatter);
        }
    }
    private static final DateTimeFormatter TWELVE_HOUR_FORMATTER = DateTimeFormatter.ofPattern("hh:mm a");
    public String formatTo12HourFormat(LocalTime localTime) {
        return localTime.format(TWELVE_HOUR_FORMATTER);
    }
}

package com.healtcare.appointments.utilities.constants;

import java.time.LocalTime;

public class AppointmentConstants {
    public static final LocalTime MORNING_START_TIME = LocalTime.of(8, 0);
    public static final LocalTime MORNING_END_TIME = LocalTime.of(12, 0);

    public static final LocalTime AFTERNOON_START_TIME = LocalTime.of(13, 0);
    public static final LocalTime AFTERNOON_END_TIME = LocalTime.of(17, 0);

    public static final LocalTime EVENING_START_TIME = LocalTime.of(17, 0);
    public static final LocalTime EVENING_END_TIME = LocalTime.of(21, 0);
}

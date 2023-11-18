package com.healtcare.appointments.utilities.constants;

import java.time.LocalTime;

public class AppointmentConstants {

    public static final Long ADVANCE_SCHEDULING_DAYS_LIMIT = 10L;
    // Time (in hours) before an appointment can be canceled.
    public static final long CANCEL_TIME_LIMIT_BEFORE_APPOINTMENT = 6;
    // Time (in minutes) after an appointment is placed can be cancelled.
    public static final long CANCEL_TIME_LIMIT_AFTER_REQUEST = 6;

    // Appointment shift names
    public static final String SHIFT1 = "morning";
    public static final String SHIFT2 = "afternoon";
    public static final String SHIFT3 = "evening";

    // Appointment types names
    public static final String TYPE1 = "Not available";
    public static final String TYPE2 = "In person";
    public static final String TYPE3 = "Telemedicine";

    // Appointment shifts times
    public static final LocalTime MORNING_START_TIME = LocalTime.of(8, 0);
    public static final LocalTime MORNING_END_TIME = LocalTime.of(12, 0);
    public static final LocalTime AFTERNOON_START_TIME = LocalTime.of(13, 0);
    public static final LocalTime AFTERNOON_END_TIME = LocalTime.of(17, 0);
    public static final LocalTime EVENING_START_TIME = LocalTime.of(17, 0);
    public static final LocalTime EVENING_END_TIME = LocalTime.of(21, 0);
}

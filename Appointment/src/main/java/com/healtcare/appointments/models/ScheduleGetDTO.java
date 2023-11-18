package com.healtcare.appointments.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ScheduleGetDTO {
    private Integer morning;
    private AvailabilityDTO morningAvailability;
    private Integer afterNoon;
    private AvailabilityDTO afternoonAvailability;
    private Integer evening;
    private AvailabilityDTO eveningAvailability;
}

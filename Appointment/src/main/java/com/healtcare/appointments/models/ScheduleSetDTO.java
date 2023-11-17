package com.healtcare.appointments.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ScheduleSetDTO {
    // Date of the appointment
    private LocalDate date;
    // Shifts and capacities
    private Integer morning;
    private Integer morningCapacity;
    private Integer afterNoon;
    private Integer afterNoonCapacity;
    private Integer evening;
    private Integer eveningCapacity;
}

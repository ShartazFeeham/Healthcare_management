package com.healtcare.appointments.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class AvailabilityDTO {
    private Integer capacity;
    private Integer booked;
    private Integer delay;
}

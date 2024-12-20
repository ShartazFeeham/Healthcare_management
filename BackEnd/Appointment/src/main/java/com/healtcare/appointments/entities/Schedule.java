package com.healtcare.appointments.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String doctorId;
    private LocalDate date;
    private Integer morningShift;
    private Integer afternoonShift;
    private Integer eveningShift;

    private Integer morningCapacity;
    private Integer afternoonCapacity;
    private Integer eveningCapacity;
}

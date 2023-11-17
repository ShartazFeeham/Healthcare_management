package com.healtcare.appointments.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Delay {
    @Id
    String doctorId;
    LocalDateTime entryTime;
    Integer delayMinutes;
}

package com.healtcare.appointments.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Delay {
    @Id
    String doctorId;
    LocalDateTime entryTime;
    @NotNull(message = "Delay minutes cannot be null")
    Integer delayMinutes;
}

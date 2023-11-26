package com.healtcare.appointments.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Appointment {
    @Id
    private String id;

    private String doctorId;
    private String patientId;
    private LocalDate date;
    private String shift;
    private String type;
    private Integer serialNo;
    private LocalDateTime appointmentTime;
    private LocalDateTime schedulingTime;
    private boolean cancelled;
}

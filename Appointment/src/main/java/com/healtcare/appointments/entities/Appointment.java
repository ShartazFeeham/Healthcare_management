package com.healtcare.appointments.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String doctorId;
    private String patientId;
    private String date;
    private String shift;
    private String type;
    private Integer serialNo;
    private LocalDateTime appointmentTime;
    private LocalDateTime schedulingTime;
    private Integer delayTime;
    private boolean cancelled;
}

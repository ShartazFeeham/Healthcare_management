package com.healtcare.appointments.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Entity
public class Review {
    @Id
    private String id;
    private String doctorId;
    private Integer rating;
    private String date;
    private String userId;
    private String comment;
}

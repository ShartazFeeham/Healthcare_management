package com.healtcare.appointments.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {
    @Id
    private String id;

    @NotNull(message = "Doctor ID cannot be null")
    private String doctorId;

    @NotNull(message = "Rating cannot be null")
    private Integer rating;

    private String date;

    private String userId;

    @NotNull(message = "Comment cannot be null")
    private String comment;
}

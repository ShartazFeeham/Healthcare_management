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
public class Equipment {
    @Id
    private String id;

    @NotNull(message = "Name cannot be null")
    private String name;
    private String photoURL;

    @NotNull(message = "Use Cases cannot be null")
    @Column(columnDefinition = "TEXT")
    private String useCases;

    @NotNull(message = "Details cannot be null")
    @Column(columnDefinition = "TEXT")
    private String details;

    @NotNull(message = "Warning cannot be null")
    @Column(columnDefinition = "TEXT")
    private String warning;

    @NotNull(message = "Costing cannot be null")
    private String costing;

    @NotNull(message = "Availability cannot be null")
    private String availability;
}

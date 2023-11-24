package com.healtcare.appointments.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private String name;
    private String photoURL;
    @Column(columnDefinition = "TEXT")
    private String useCases;
    @Column(columnDefinition = "TEXT")
    private String details;
    @Column(columnDefinition = "TEXT")
    private String warning;
    private String costing;
    private String availability;
}
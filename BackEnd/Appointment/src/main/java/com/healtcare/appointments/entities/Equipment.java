package com.healtcare.appointments.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Entity
public class Equipment {
    @Id
    private String id;
    private String name;
    private String useCases;
    private String details;
    private String costing;
    private String availability;
}

package com.healthcare.medicines.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Medicine {
    @Id
    @NotNull(message = "ID cannot be null")
    private String id;

    @NotNull(message = "Commercial Name cannot be null")
    private String commercialName;

    @NotNull(message = "Medicine Name cannot be null")
    private String medicineName;

    @NotNull(message = "Classification cannot be null")
    private String classification;

    @NotNull(message = "Description cannot be null")
    @Size(min = 50, max = 1000, message = "Description must be between 50 and 1000 characters")
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Dosage Form cannot be null")
    private String dosageForm;

    @NotNull(message = "Strength Volume cannot be null")
    private String strengthVolume;

    @NotNull(message = "Strength Weight cannot be null")
    private String strengthWeight;

    @NotNull(message = "Warnings cannot be null")
    private String warnings;

    @NotNull(message = "Adverse Effects cannot be null")
    private String adverseEffects;

    @NotNull(message = "Manufacturer cannot be null")
    private String manufacturer;

    @NotNull(message = "National Drug Code cannot be null")
    private String nationalDrugCode;

    private LocalDate expirationDate;

    // Photo URL can be null
    private String photoUrl;
}

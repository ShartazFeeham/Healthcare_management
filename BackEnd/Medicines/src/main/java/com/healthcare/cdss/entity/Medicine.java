package com.healthcare.cdss.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Medicine {
    @Id
    private String id;
    private String commercialName;
    private String medicineName;
    private String classification;
    private String description;
    private String dosageForm;
    private String strengthVolume;
    private String strengthWeight;
    private String warnings;
    private String adverseEffects;
    private String manufacturer;
    private String nationalDrugCode;
    private LocalDate expirationDate;
    private String photoUrl;
}
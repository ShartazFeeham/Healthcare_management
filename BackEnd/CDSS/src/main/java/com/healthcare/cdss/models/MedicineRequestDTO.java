package com.healthcare.cdss.models;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class MedicineRequestDTO {
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
    private String expirationDate;
    private String photo;
}
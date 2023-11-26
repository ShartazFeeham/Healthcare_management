package com.healthcare.medicines.models;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class MedicineResponseDTO {
    private String medicineId;
    private String commercialName;
    private String medicineName;
    private String dosageForm;
    private String strengthVolume;
    private String strengthWeight;
    private String manufacturer;
    private String expirationDate;
}

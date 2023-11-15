package com.healthcare.medicines.models;

import java.util.List;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class PatientHealthDTO {
    private int weight;
    private int age;
    private String bloodPressure;
    private String diabetes;
    private String asthma;
    private int height;
    private List<String> allergies;
    private String occupation;
    private String smoking;
    private String drinking;
}
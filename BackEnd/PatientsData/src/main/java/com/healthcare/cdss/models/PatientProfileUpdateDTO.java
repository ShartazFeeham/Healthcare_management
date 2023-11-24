package com.healthcare.cdss.models;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class PatientProfileUpdateDTO {
    private String firstName;
    private String lastName;
    private String gender;
    private String profilePhoto;
    private String allergies;
    private int age;
    private int height;
    private int weight;
    private String bloodGroup;
    private String bloodSugar;
    private String bloodPressure;
    private String occupation;
    private String phoneNo;
    private String residence;
    private boolean smoking;
    private boolean drinking;
    private boolean asthma;
}

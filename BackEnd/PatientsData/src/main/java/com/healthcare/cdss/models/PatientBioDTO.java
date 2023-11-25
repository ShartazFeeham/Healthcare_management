package com.healthcare.cdss.models;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class PatientBioDTO {
    private String patientId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String gender;
    private String bloodGroup;
    private String profilePhoto;
}

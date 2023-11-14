package com.healthcare.patientsdata.models;

import jakarta.persistence.Entity;
import lombok.*;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ReadDoctorPersonalInfoDTO {
    private String phone;
    private String dateOfBirth;
    private String nidNo;
    private String residence;
}

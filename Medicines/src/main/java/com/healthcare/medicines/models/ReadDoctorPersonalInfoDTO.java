package com.healthcare.medicines.models;

import lombok.*;

 @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ReadDoctorPersonalInfoDTO {
    private String phone;
    private String dateOfBirth;
    private String nidNo;
    private String residence;
}

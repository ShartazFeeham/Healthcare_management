package com.healthcare.medicines.models;

import com.healthcare.medicines.entity.Certification;
import com.healthcare.medicines.entity.Qualification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class DoctorEditExistingDTO {
    private String doctorId;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String bio;
    private Integer experience;
    private String license;
    private String specializations;
    private String phoneNumber;
    private String residence;
    private String photo;
    private List<Qualification> qualifications;
    private List<Certification> certifications;
}

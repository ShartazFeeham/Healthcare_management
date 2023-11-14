package com.healthcare.medicines.models;

import com.healthcare.medicines.entity.Certification;
import com.healthcare.medicines.entity.Qualification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UpdateDoctorProfileDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String specialization;
    private String residence;
    private String bio;
    private Integer experience;
    private File photo;
    private List<Qualification> qualifications;
    private List<Certification> certifications;
}

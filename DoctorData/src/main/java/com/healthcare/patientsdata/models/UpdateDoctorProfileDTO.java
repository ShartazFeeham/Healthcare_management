package com.healthcare.patientsdata.models;

import com.healthcare.patientsdata.entity.Certification;
import com.healthcare.patientsdata.entity.Qualification;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.util.List;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
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

package com.healthcare.patientsdata.models;

import com.healthcare.patientsdata.entity.Certification;
import com.healthcare.patientsdata.entity.Qualification;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ReadDoctorProfileDTO {
    private String doctorId;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String profilePhoto;
    private String bio;
    private String experience;
    private String license;
    private String specializations;
    private List<Qualification> qualifications;
    private List<Certification> certifications;
}

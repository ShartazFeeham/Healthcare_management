package com.healthcare.patientsdata.models;

import com.healthcare.patientsdata.entity.Certification;
import com.healthcare.patientsdata.entity.Qualification;

import java.io.File;
import java.util.List;

import jakarta.persistence.Entity;
import lombok.*;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CreateDoctorAccountDTO {
    private String bio;
    private List<Certification> certifications;
    private String dateOfBirth;
    private String email;
    private int experience;
    private String firstName;
    private String gender;
    private String lastName;
    private String license;
    private String nid;
    private String password;
    private String phoneNumber;
    private File photo;
    private List<Qualification> qualifications;
    private String residence;
    private String specialization;
}

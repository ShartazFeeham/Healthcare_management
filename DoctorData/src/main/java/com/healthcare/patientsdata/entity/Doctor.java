package com.healthcare.patientsdata.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    private String doctorId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private String profilePhoto;
    private String bio;
    private Integer experience;
    private String license;
    private String specializations;
    private String phoneNumber;
    private String residence;

    // Doctor Personal Info
    private String dateOfBirth;
    private String nidNo;
    private List<Qualification> qualifications;
    private List<Certification> certifications;
}

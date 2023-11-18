package com.healthcare.medicines.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Doctor {
    @Id
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
    @OneToMany(cascade = CascadeType.ALL)
    private List<Qualification> qualifications;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Certification> certifications;
}

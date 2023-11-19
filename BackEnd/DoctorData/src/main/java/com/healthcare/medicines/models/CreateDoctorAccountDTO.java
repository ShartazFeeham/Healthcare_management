package com.healthcare.medicines.models;

import com.healthcare.medicines.entity.Certification;
import com.healthcare.medicines.entity.Qualification;

import java.io.File;
import java.util.List;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
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
    private MultipartFile photo;
    private List<Qualification> qualifications;
    private String residence;
    private String specialization;
}

package com.healthcare.cdss.models;

import com.healthcare.cdss.entity.Certification;
import com.healthcare.cdss.entity.Qualification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ReadDoctorProfileDTO {
    private String doctorId;
    private String firstName;
    private String lastName;
    private String email;
    private String room;
    private String gender;
    private String profilePhoto;
    private String bio;
    private Integer experience;
    private String license;
    private String specializations;
    private List<Qualification> qualifications;
    private List<Certification> certifications;
}

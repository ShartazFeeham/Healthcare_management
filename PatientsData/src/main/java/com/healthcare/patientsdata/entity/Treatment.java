package com.healthcare.patientsdata.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Treatment {
    private String diseaseName;
    private String treatedBy;
    private String issueDate;
    private String closingDate;
    private String doctorComment;
    private String progression;
    @OneToMany(mappedBy = "treatment", cascade = CascadeType.ALL)
    private List<Medicine> medicines;
    @OneToMany(mappedBy = "treatment", cascade = CascadeType.ALL)
    private List<Diagnosis> diagnoses;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonIgnore
    private Patient patient;
}
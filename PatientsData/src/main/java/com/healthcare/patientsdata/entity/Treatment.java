package com.healthcare.patientsdata.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private String doctorId;
}
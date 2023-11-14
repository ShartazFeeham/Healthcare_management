package com.healthcare.medicines.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String diseaseName;
    private String reportWriter;
    private String issueDate;
    private String closingDate;
    private String doctorComment;
    private String progression;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Medicine> medicines;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Diagnosis> diagnoses;
    private String appointmentId;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonIgnore
    private Patient patient;
    private String doctorId;
}
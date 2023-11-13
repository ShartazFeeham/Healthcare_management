package com.healthcare.patientsdata.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String medicineId;
    private String name;

    @ManyToOne
    @JoinColumn(name = "treatment_id")
    @JsonIgnore
    private Treatment treatment;
}

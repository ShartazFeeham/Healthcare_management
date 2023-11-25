package com.healthcare.cdss.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Arrays;
import java.util.List;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Patient {
    @Id
    private String userId;

    // Patient bio properties
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String gender;
    private String bloodGroup;
    private String profilePhoto;

    // Patient health-related properties
    private int weight;
    private int age;
    private String bloodPressure;
    private String diabetes;
    private int height;
    private List<String> allergies;
    private String occupation;
    private boolean smoking;
    private boolean drinking;
    private boolean asthma;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<AchievementProgress> achievementProgresses;

    public void setAllergies(String allergies) {
        this.allergies = Arrays.stream(allergies.trim().replace(" ", "").split(",")).toList();
    }
}

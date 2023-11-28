package com.healthcare.patients.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;

import lombok.*;

import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient {
    @Id
    private String userId;

    // Patient bio properties
    @NotBlank(message = "First name cannot be blank")
    @Size(min = 2, message = "First name must be at least 2 characters long.")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 2, message = "Last name must be at least 2 characters long.")
    private String lastName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    private String phone;

    private String address;
    @NotBlank(message = "Gender cannot be blank")
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

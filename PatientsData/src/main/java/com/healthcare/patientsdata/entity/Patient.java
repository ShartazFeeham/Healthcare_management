package com.healthcare.patientsdata.entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import java.util.List;
@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Patient {
    @Id
    private String userId;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String gender;
    private String bloodGroup;
    private String profilePhoto;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<AchievementProgress> achievementProgresses;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Treatment> treatments;
}

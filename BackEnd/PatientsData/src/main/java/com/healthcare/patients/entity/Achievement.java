package com.healthcare.patients.entity;

import com.healthcare.patients.enums.AchievementDifficulty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String goalDescription;
    private Double goalScore;
    private AchievementDifficulty difficulty;
    private String logoURL;
}
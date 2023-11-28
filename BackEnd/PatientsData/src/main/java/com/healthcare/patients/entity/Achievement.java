package com.healthcare.patients.entity;

import com.healthcare.patients.enums.AchievementDifficulty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Goal Description cannot be null")
    @Size(min = 20, max = 100, message = "Goal Description must be between 20 and 100 characters")
    private String goalDescription;

    @NotNull(message = "Goal Score cannot be null")
    private Double goalScore;

    @NotNull(message = "Difficulty cannot be null")
    private AchievementDifficulty difficulty;
    private String logoURL;
}

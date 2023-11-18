package com.healthcare.medicines.models;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class AchievementProgressDTO {
    private String title;
    private String difficulty;
    private int completedIn;
    private String completionDate;
    private String logoUrl;
}

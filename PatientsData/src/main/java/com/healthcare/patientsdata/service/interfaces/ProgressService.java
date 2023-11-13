package com.healthcare.patientsdata.service.interfaces;

import com.healthcare.patientsdata.entity.AchievementProgress;

import java.util.List;

public interface ProgressService {
    List<AchievementProgress> readAllByUser(String userId);
    void update(String userId, Long achievementId, double score);
}

package com.healthcare.patientsdata.service.interfaces;

import com.healthcare.patientsdata.entity.AchievementProgress;
import com.healthcare.patientsdata.exceptions.ItemNotFoundException;

import java.util.List;

public interface ProgressService {
    List<AchievementProgress> readAllByUser(String userId) throws ItemNotFoundException;
    void update(String userId, Long achievementId, double score) throws ItemNotFoundException;
}

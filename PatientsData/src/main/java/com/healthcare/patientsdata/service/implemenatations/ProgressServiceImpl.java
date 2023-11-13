package com.healthcare.patientsdata.service.implemenatations;

import com.healthcare.patientsdata.entity.AchievementProgress;
import com.healthcare.patientsdata.repository.ProgressRepository;
import com.healthcare.patientsdata.service.interfaces.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgressServiceImpl implements ProgressService {

    private final ProgressRepository progressRepository;

    @Override
    public List<AchievementProgress> readAllByUser(String userId) {
        return progressRepository.findAllByPatientUserId(userId);
    }

    @Override
    public void update(String userId, Long achievementId, double score) {
        // Validation logic
        AchievementProgress progress = progressRepository.findByPatientUserIdAndAchievementId(userId, achievementId)
                .orElseThrow(() -> new RuntimeException("Progress not found for user ID: " + userId +
                        " and achievement ID: " + achievementId));
        // Update the score
        progress.setScore(score);
        progressRepository.save(progress);
    }
}

package com.healthcare.patientsdata.service.interfaces;

import com.healthcare.patientsdata.entity.AchievementProgress;
import com.healthcare.patientsdata.exceptions.AccessMismatchException;
import com.healthcare.patientsdata.exceptions.DuplicateEntityException;
import com.healthcare.patientsdata.exceptions.ItemNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface ProgressService {
    List<AchievementProgress> readAllByUser(String userId) throws ItemNotFoundException;
    void acceptChallenge(Long achievementId) throws ItemNotFoundException, DuplicateEntityException;
    void cancelChallenge(Long progressId) throws ItemNotFoundException, AccessMismatchException;
    String addScore(Long progressId, double score, LocalDate date) throws ItemNotFoundException;
    void deleteScore(Long scoreId) throws ItemNotFoundException;
}

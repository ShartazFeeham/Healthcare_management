package com.healthcare.patients.services.interfaces;

import com.healthcare.patients.entity.AchievementProgress;
import com.healthcare.patients.exceptions.AccessMismatchException;
import com.healthcare.patients.exceptions.DuplicateEntityException;
import com.healthcare.patients.exceptions.ItemNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface ProgressService {
    List<AchievementProgress> readAllByUser(String userId) throws ItemNotFoundException;
    void acceptChallenge(Long achievementId) throws ItemNotFoundException, DuplicateEntityException;
    void cancelChallenge(Long progressId) throws ItemNotFoundException, AccessMismatchException;
    String addScore(Long progressId, double score, LocalDate date) throws ItemNotFoundException, AccessMismatchException;
    void deleteScore(Long scoreId) throws ItemNotFoundException, AccessMismatchException;
}

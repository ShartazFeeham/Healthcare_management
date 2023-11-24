package com.healthcare.cdss.service.interfaces;

import com.healthcare.cdss.entity.AchievementProgress;
import com.healthcare.cdss.exceptions.AccessMismatchException;
import com.healthcare.cdss.exceptions.DuplicateEntityException;
import com.healthcare.cdss.exceptions.ItemNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface ProgressService {
    List<AchievementProgress> readAllByUser(String userId) throws ItemNotFoundException;
    void acceptChallenge(Long achievementId) throws ItemNotFoundException, DuplicateEntityException;
    void cancelChallenge(Long progressId) throws ItemNotFoundException, AccessMismatchException;
    String addScore(Long progressId, double score, LocalDate date) throws ItemNotFoundException, AccessMismatchException;
    void deleteScore(Long scoreId) throws ItemNotFoundException, AccessMismatchException;
}

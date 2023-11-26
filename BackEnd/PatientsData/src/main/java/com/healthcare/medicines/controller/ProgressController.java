package com.healthcare.medicines.controller;

import com.healthcare.medicines.entity.AchievementProgress;
import com.healthcare.medicines.exceptions.AccessMismatchException;
import com.healthcare.medicines.exceptions.DuplicateEntityException;
import com.healthcare.medicines.exceptions.ItemNotFoundException;
import com.healthcare.medicines.services.interfaces.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
@RestController
@RequestMapping("/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<AchievementProgress>> getAllProgressByUser(@PathVariable String userId) throws ItemNotFoundException {
        List<AchievementProgress> progressList = progressService.readAllByUser(userId);
        return ResponseEntity.ok(progressList);
    }

    @PutMapping("/accept-challenge/{achievementId}")
    public ResponseEntity<String> acceptChallenge(@PathVariable Long achievementId) throws ItemNotFoundException, DuplicateEntityException, DuplicateEntityException {
        progressService.acceptChallenge(achievementId);
        return ResponseEntity.ok("Challenge accepted successfully");
    }

    @DeleteMapping("/cancel-challenge/{progressId}")
    public ResponseEntity<String> cancelChallenge(@PathVariable Long progressId) throws ItemNotFoundException, AccessMismatchException {
        progressService.cancelChallenge(progressId);
        return ResponseEntity.ok("Challenge cancelled successfully");
    }

    @PostMapping("/add-score/{progressId}/{score}/{date}")
    public ResponseEntity<String> addScore(@PathVariable Long progressId, @PathVariable double score,
                                           @PathVariable String date) throws ItemNotFoundException, AccessMismatchException {
        LocalDate scoreDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String response = progressService.addScore(progressId, score, scoreDate);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-score/{scoreId}")
    public ResponseEntity<String> deleteScore(@PathVariable Long scoreId) throws ItemNotFoundException, AccessMismatchException {
        progressService.deleteScore(scoreId);
        return ResponseEntity.ok("Score deleted successfully");
    }
}

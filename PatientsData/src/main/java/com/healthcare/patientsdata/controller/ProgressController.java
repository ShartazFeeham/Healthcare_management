package com.healthcare.patientsdata.controller;

import com.healthcare.patientsdata.entity.AchievementProgress;
import com.healthcare.patientsdata.exceptions.ItemNotFoundException;
import com.healthcare.patientsdata.service.interfaces.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{userId}/update")
    public ResponseEntity<String> updateProgress(@PathVariable String userId, @RequestParam Long achievementId, @RequestParam double score) throws ItemNotFoundException {
        progressService.update(userId, achievementId, score);
        return ResponseEntity.ok("Progress updated successfully");
    }
}

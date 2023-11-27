package com.healthcare.patients.controller;

import com.healthcare.patients.entity.Achievement;
import com.healthcare.patients.exceptions.ItemNotFoundException;
import com.healthcare.patients.services.interfaces.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/achievements")
@RequiredArgsConstructor
public class AchievementController {

    private final AchievementService achievementService;

    @PostMapping
    public ResponseEntity<String> createAchievement(@RequestBody Achievement achievementDTO) {
        achievementService.create(achievementDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Achievement created successfully");
    }

    @GetMapping("/{achievementId}")
    public ResponseEntity<Achievement> readAchievement(@PathVariable Long achievementId) throws ItemNotFoundException {
        Achievement achievement = achievementService.read(achievementId);
        return ResponseEntity.ok(achievement);
    }

    @GetMapping
    public ResponseEntity<List<Achievement>> readAllAchievements() {
        List<Achievement> achievements = achievementService.readAll();
        return ResponseEntity.ok(achievements);
    }

    @PutMapping("/{achievementId}")
    public ResponseEntity<String> updateAchievement(@PathVariable Long achievementId, @RequestBody Achievement achievementDTO) throws ItemNotFoundException {
        achievementDTO.setId(achievementId);
        achievementService.update(achievementDTO);
        return ResponseEntity.ok("Achievement updated successfully");
    }

    @DeleteMapping("/{achievementId}")
    public ResponseEntity<String> deleteAchievement(@PathVariable Long achievementId) throws ItemNotFoundException {
        achievementService.delete(achievementId);
        return ResponseEntity.ok("Achievement deleted successfully");
    }
}

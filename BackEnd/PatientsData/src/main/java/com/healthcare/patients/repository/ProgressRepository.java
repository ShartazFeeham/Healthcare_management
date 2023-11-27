package com.healthcare.patients.repository;

import com.healthcare.patients.entity.AchievementProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressRepository extends JpaRepository<AchievementProgress, Long> {
}

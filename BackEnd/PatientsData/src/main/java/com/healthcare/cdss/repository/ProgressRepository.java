package com.healthcare.cdss.repository;

import com.healthcare.cdss.entity.AchievementProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressRepository extends JpaRepository<AchievementProgress, Long> {
}

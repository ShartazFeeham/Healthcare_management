package com.healthcare.medicines.repository;

import com.healthcare.medicines.entity.AchievementProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressRepository extends JpaRepository<AchievementProgress, Long> {
}

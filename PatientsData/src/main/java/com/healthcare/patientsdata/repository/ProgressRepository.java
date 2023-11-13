package com.healthcare.patientsdata.repository;

import com.healthcare.patientsdata.entity.AchievementProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressRepository extends JpaRepository<AchievementProgress, Long> {
}

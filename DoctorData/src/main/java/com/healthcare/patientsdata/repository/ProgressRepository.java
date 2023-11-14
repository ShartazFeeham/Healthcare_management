package com.healthcare.patientsdata.repository;

import com.healthcare.patientsdata.entity.AchievementProgress;
import com.healthcare.patientsdata.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgressRepository extends JpaRepository<AchievementProgress, Long> {
}

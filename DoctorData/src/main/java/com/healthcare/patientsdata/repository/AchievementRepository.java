package com.healthcare.patientsdata.repository;

import com.healthcare.patientsdata.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
}

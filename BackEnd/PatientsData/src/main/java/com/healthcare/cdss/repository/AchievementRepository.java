package com.healthcare.cdss.repository;

import com.healthcare.cdss.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
}

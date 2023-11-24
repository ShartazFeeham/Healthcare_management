package com.healthcare.cdss.repository;

import com.healthcare.cdss.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Long> {
}

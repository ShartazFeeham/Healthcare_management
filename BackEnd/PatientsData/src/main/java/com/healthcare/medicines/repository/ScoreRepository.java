package com.healthcare.medicines.repository;

import com.healthcare.medicines.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Long> {
}

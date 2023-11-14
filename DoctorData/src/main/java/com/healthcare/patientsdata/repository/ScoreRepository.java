package com.healthcare.patientsdata.repository;

import com.healthcare.patientsdata.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Long> {
}

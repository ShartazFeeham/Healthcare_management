package com.healthcare.patientsdata.repository;

import com.healthcare.patientsdata.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
}

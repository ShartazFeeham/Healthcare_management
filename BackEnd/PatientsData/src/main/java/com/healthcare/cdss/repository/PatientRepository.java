package com.healthcare.cdss.repository;

import com.healthcare.cdss.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, String> {
    long countByUserIdStartingWith(String idPattern);
}

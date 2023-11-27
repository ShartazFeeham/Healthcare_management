package com.healthcare.patients.repository;

import com.healthcare.patients.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, String> {
    long countByUserIdStartingWith(String idPattern);
}

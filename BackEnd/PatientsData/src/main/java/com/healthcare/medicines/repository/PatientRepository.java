package com.healthcare.medicines.repository;

import com.healthcare.medicines.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, String> {
    long countByUserIdStartingWith(String idPattern);
}

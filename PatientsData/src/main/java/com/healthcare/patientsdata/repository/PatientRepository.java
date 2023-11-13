package com.healthcare.patientsdata.repository;

import com.healthcare.patientsdata.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, String> {
}

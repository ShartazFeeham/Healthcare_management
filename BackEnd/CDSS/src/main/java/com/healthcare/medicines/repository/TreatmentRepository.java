package com.healthcare.medicines.repository;

import com.healthcare.medicines.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    List<Treatment> findByPatientId(String patientId);
    List<Treatment> findByAuthorId(String doctorId);
}

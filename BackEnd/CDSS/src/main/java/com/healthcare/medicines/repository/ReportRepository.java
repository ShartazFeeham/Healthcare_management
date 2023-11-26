package com.healthcare.medicines.repository;

import com.healthcare.medicines.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByPatientId(String patientId);
}

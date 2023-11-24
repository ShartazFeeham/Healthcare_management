package com.healthcare.cdss.repository;

import com.healthcare.cdss.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByPatientId(String patientId);
}

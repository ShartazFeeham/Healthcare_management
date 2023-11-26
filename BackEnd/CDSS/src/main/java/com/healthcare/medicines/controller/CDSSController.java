package com.healthcare.medicines.controller;

import com.healthcare.medicines.entity.Report;
import com.healthcare.medicines.entity.Treatment;
import com.healthcare.medicines.exceptions.AccessDeniedException;
import com.healthcare.medicines.exceptions.CustomException;
import com.healthcare.medicines.exceptions.InvalidRequestException;
import com.healthcare.medicines.services.CDSSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cdss")
@RequiredArgsConstructor
public class CDSSController {

    private final CDSSService cdssService;

    @GetMapping("/similar/{patientId}")
    public ResponseEntity<List<Treatment>> getSimilarTreatments(@PathVariable String patientId) throws InvalidRequestException {
        List<Treatment> similarTreatments = cdssService.getSimilar(patientId);
        return ResponseEntity.ok(similarTreatments);
    }

    @GetMapping("/report/generate/{patientId}")
    public ResponseEntity<String> getReport(@PathVariable String patientId) throws CustomException {
        return ResponseEntity.ok(cdssService.getReport(patientId));
    }

    @GetMapping("/report/list/{patientId}")
    public ResponseEntity<List<Report>> getAllReport(@PathVariable String patientId) throws InvalidRequestException, AccessDeniedException {
        return ResponseEntity.ok(cdssService.getReports(patientId));
    }
}

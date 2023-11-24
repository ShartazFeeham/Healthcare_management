package com.healthcare.cdss.controller;

import com.healthcare.cdss.entity.Treatment;
import com.healthcare.cdss.exceptions.InvalidRequestException;
import com.healthcare.cdss.service.CDSSService;
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

    @GetMapping("/report/{patientId}")
    public ResponseEntity<String> getReport(@PathVariable String patientId) throws InvalidRequestException {
        return ResponseEntity.ok(cdssService.getReport(patientId));
    }

    @GetMapping("/hello")
    public String h(){return "Hello";}
}

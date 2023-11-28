package com.healthcare.cdss.controller;

import com.healthcare.cdss.exceptions.AccessDeniedException;
import com.healthcare.cdss.exceptions.InvalidRequestException;
import com.healthcare.cdss.exceptions.ItemNotFoundException;
import com.healthcare.cdss.services.TreatmentService;
import com.healthcare.cdss.entity.Treatment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treatments")
@RequiredArgsConstructor
public class TreatmentController {

    private final TreatmentService treatmentService;

    // Endpoint to create a new treatment
    @PostMapping
    public ResponseEntity<String> createTreatment(@RequestBody Treatment treatment) throws InvalidRequestException {
        treatmentService.create(treatment);
        return ResponseEntity.status(HttpStatus.CREATED).body("Treatment created successfully.");
    }

    // Endpoint to update an existing treatment
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTreatment(@PathVariable Long id, @RequestBody Treatment treatment) throws AccessDeniedException, InvalidRequestException, ItemNotFoundException {
        treatmentService.update(id, treatment);
        return ResponseEntity.ok("Treatment updated successfully.");
    }

    // Endpoint to delete a treatment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTreatment(@PathVariable Long id) throws AccessDeniedException, ItemNotFoundException {
        treatmentService.delete(id);
        return ResponseEntity.ok("Treatment deleted successfully.");
    }

    // Endpoint to get a treatment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Treatment> getTreatmentById(@PathVariable Long id) throws ItemNotFoundException, AccessDeniedException {
        return ResponseEntity.ok(treatmentService.getById(id));
    }

    // Endpoint to get all treatments for a specific patient
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Treatment>> getTreatmentsByPatientId(@PathVariable String patientId) throws AccessDeniedException {
        List<Treatment> treatments = treatmentService.getByPatientId(patientId);
        return ResponseEntity.ok(treatments);
    }

    // Endpoint to get all treatments authored by a specific doctor
    @GetMapping("/author/{doctorId}")
    public ResponseEntity<List<Treatment>> getTreatmentsByAuthorId(@PathVariable String doctorId) throws AccessDeniedException {
        List<Treatment> treatments = treatmentService.getByAuthorId(doctorId);
        return ResponseEntity.ok(treatments);
    }
}

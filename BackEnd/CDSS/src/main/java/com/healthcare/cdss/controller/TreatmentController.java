package com.healthcare.cdss.controller;

import com.healthcare.cdss.entity.Treatment;
import com.healthcare.cdss.exceptions.AccessDeniedException;
import com.healthcare.cdss.exceptions.InvalidRequestException;
import com.healthcare.cdss.exceptions.ItemNotFoundException;
import com.healthcare.cdss.service.TreatmentService;
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

    @PostMapping
    public ResponseEntity<String> createTreatment(@RequestBody Treatment treatment) throws InvalidRequestException {
        treatmentService.create(treatment);
        return ResponseEntity.status(HttpStatus.CREATED).body("Treatment created successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTreatment(@PathVariable Long id, @RequestBody Treatment treatment) throws AccessDeniedException, InvalidRequestException, ItemNotFoundException {
        treatmentService.update(id, treatment);
        return ResponseEntity.ok("Treatment updated successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTreatment(@PathVariable Long id) throws AccessDeniedException, ItemNotFoundException {
        treatmentService.delete(id);
        return ResponseEntity.ok("Treatment deleted successfully.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Treatment> getTreatmentById(@PathVariable Long id) throws ItemNotFoundException, AccessDeniedException {
        return ResponseEntity.ok(treatmentService.getById(id));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Treatment>> getTreatmentsByPatientId(@PathVariable String patientId) throws AccessDeniedException {
        List<Treatment> treatments = treatmentService.getByPatientId(patientId);
        return ResponseEntity.ok(treatments);
    }

    @GetMapping("/author/{doctorId}")
    public ResponseEntity<List<Treatment>> getTreatmentsByAuthorId(@PathVariable String doctorId) throws AccessDeniedException {
        List<Treatment> treatments = treatmentService.getByAuthorId(doctorId);
        return ResponseEntity.ok(treatments);
    }
}

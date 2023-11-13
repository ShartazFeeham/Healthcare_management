package com.healthcare.patientsdata.controller;

import com.healthcare.patientsdata.entity.Treatment;
import com.healthcare.patientsdata.exceptions.ItemNotFoundException;
import com.healthcare.patientsdata.service.interfaces.TreatmentService;
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

    @GetMapping("/{userId}")
    public ResponseEntity<List<Treatment>> getPatientTreatments(@PathVariable String userId) {
        try {
            List<Treatment> treatments = treatmentService.getPatientTreatments(userId);
            return ResponseEntity.ok(treatments);
        } catch (ItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{treatmentId}")
    public ResponseEntity<Treatment> getTreatmentById(@PathVariable Long treatmentId) {
        try {
            Treatment treatment = treatmentService.read(treatmentId);
            return ResponseEntity.ok(treatment);
        } catch (ItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/{userId}")
    public ResponseEntity<String> createTreatment(@PathVariable String userId, @RequestBody Treatment treatmentDTO) {
        try {
            treatmentService.create(userId, treatmentDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Treatment created successfully");
        } catch (ItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> updateTreatment(@RequestBody Treatment treatmentDTO) {
        try {
            treatmentService.update(treatmentDTO);
            return ResponseEntity.ok("Treatment updated successfully");
        } catch (ItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{treatmentId}")
    public ResponseEntity<String> deleteTreatment(@PathVariable Long treatmentId) {
        try {
            treatmentService.delete(treatmentId);
            return ResponseEntity.ok("Treatment deleted successfully");
        } catch (ItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

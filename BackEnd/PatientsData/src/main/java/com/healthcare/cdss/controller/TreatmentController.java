package com.healthcare.cdss.controller;

import com.healthcare.cdss.entity.Treatment;
import com.healthcare.cdss.exceptions.AccessMismatchException;
import com.healthcare.cdss.exceptions.ItemNotFoundException;
import com.healthcare.cdss.service.interfaces.TreatmentService;
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

    @GetMapping("/by-patients/{userId}")
    public ResponseEntity<List<Treatment>> getPatientTreatments(@PathVariable String userId) throws ItemNotFoundException {
        List<Treatment> treatments = treatmentService.getPatientTreatments(userId);
        return ResponseEntity.ok(treatments);
    }

    @GetMapping("/{treatmentId}")
    public ResponseEntity<Treatment> getTreatmentById(@PathVariable Long treatmentId) throws ItemNotFoundException {
        Treatment treatment = treatmentService.read(treatmentId);
        return ResponseEntity.ok(treatment);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<String> createTreatment(@PathVariable String userId, @RequestBody Treatment treatmentDTO) throws ItemNotFoundException, AccessMismatchException {
        treatmentService.create(userId, treatmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Treatment created successfully");
    }

    @PutMapping("/{treatmentId}")
    public ResponseEntity<String> updateTreatment(@PathVariable Long treatmentId, @RequestBody Treatment treatmentDTO) throws ItemNotFoundException, AccessMismatchException {
        treatmentService.update(treatmentId, treatmentDTO);
        return ResponseEntity.ok("Treatment updated successfully");
    }

    @DeleteMapping("/{treatmentId}")
    public ResponseEntity<String> deleteTreatment(@PathVariable Long treatmentId) throws ItemNotFoundException, AccessMismatchException {
        treatmentService.delete(treatmentId);
        return ResponseEntity.ok("Treatment deleted successfully");
    }
}

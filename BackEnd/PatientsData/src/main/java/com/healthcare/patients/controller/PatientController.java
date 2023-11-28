package com.healthcare.patients.controller;

import com.healthcare.patients.entity.Patient;
import com.healthcare.patients.exceptions.CustomException;
import com.healthcare.patients.exceptions.InternalCommunicationException;
import com.healthcare.patients.exceptions.ItemNotFoundException;
import com.healthcare.patients.models.*;
import com.healthcare.patients.models.*;
import com.healthcare.patients.services.interfaces.PatientService;
import com.healthcare.patients.utilities.token.IDExtractor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerPatient(@RequestBody PatientRegisterDTO patientRegisterDTO) throws InternalCommunicationException {
        patientService.register(patientRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Patient registered successfully");
    }

    @GetMapping("/{userId}/bio")
    public ResponseEntity<PatientBioDTO> getPatientBio(@PathVariable String userId) throws ItemNotFoundException {
        PatientBioDTO bioDTO = patientService.getPatientBio(userId);
        return ResponseEntity.ok(bioDTO);
    }

    @GetMapping("/{userId}/health")
    public ResponseEntity<PatientHealthDTO> getPatientHealth(@PathVariable String userId) throws CustomException {
        PatientHealthDTO healthDTO = patientService.getPatientHealth(userId);
        return ResponseEntity.ok(healthDTO);
    }

    @PutMapping("/update-profile")
    public ResponseEntity<String> updatePatientProfile(@RequestBody PatientProfileUpdateDTO request) throws ItemNotFoundException {
        patientService.updatePatientProfile(IDExtractor.getUserID(), request);
        return ResponseEntity.ok("Patient profile updated successfully");
    }

    @GetMapping("/minimal-info/{userId}")
    public ResponseEntity<UserMinimalInfoDTO> updatePatientProfile(@PathVariable String userId) throws ItemNotFoundException {
        return ResponseEntity.ok(patientService.getUserMinimalInfo(userId));
    }

    @GetMapping("total-count")
    public ResponseEntity<Integer> getTotalCount(){
        return ResponseEntity.ok(patientService.patientCount());
    }

    @GetMapping("/list")
    public ResponseEntity<List<Patient>> getAllPatients(){
        return ResponseEntity.ok(patientService.getAll());
    }
}

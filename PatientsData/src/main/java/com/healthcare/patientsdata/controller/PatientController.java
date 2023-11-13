package com.healthcare.patientsdata.controller;

import com.healthcare.patientsdata.exceptions.InternalCommunicationException;
import com.healthcare.patientsdata.exceptions.ItemNotFoundException;
import com.healthcare.patientsdata.models.PatientBioDTO;
import com.healthcare.patientsdata.models.PatientHealthDTO;
import com.healthcare.patientsdata.models.PatientProfileUpdateDTO;
import com.healthcare.patientsdata.models.PatientRegisterDTO;
import com.healthcare.patientsdata.service.interfaces.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<PatientHealthDTO> getPatientHealth(@PathVariable String userId) throws ItemNotFoundException {
        PatientHealthDTO healthDTO = patientService.getPatientHealth(userId);
        return ResponseEntity.ok(healthDTO);
    }

    @PutMapping("/{userId}/update-profile")
    public ResponseEntity<String> updatePatientProfile(@PathVariable String userId, @RequestBody PatientProfileUpdateDTO request) throws ItemNotFoundException {
        patientService.updatePatientProfile(userId, request);
        return ResponseEntity.ok("Patient profile updated successfully");
    }
}

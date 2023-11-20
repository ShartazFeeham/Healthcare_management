package com.healthcare.medicines.controller;

import com.healthcare.medicines.exceptions.AccessMismatchException;
import com.healthcare.medicines.exceptions.ItemNotFoundException;
import com.healthcare.medicines.models.*;
import com.healthcare.medicines.service.interfaces.DoctorService;
import com.healthcare.medicines.utilities.token.IDExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/register")
    public ResponseEntity<String> registerDoctor(@RequestBody CreateDoctorAccountDTO createDoctorAccountDTO) {
        doctorService.register(createDoctorAccountDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Doctor registered successfully");
    }

    @GetMapping("/{userId}/personal-info")
    public ResponseEntity<ReadDoctorPersonalInfoDTO> getPersonalInfo(@PathVariable String userId) throws ItemNotFoundException, ItemNotFoundException {
        ReadDoctorPersonalInfoDTO personalInfo = doctorService.readPersonalInfo(userId);
        return ResponseEntity.ok(personalInfo);
    }

    @GetMapping("/{userId}/profile-info")
    public ResponseEntity<ReadDoctorProfileDTO> getProfileInfo(@PathVariable String userId) throws ItemNotFoundException {
        ReadDoctorProfileDTO profileInfo = doctorService.readDoctorProfileInfo(userId);
        return ResponseEntity.ok(profileInfo);
    }

    @GetMapping("/existing-edit-data")
    public ResponseEntity<DoctorEditExistingDTO> getEditExistingInfo() throws ItemNotFoundException {
        DoctorEditExistingDTO profileInfo = doctorService.editExisting();
        return ResponseEntity.ok(profileInfo);
    }

    @PutMapping("/edit-profile")
    public ResponseEntity<String> updateDoctorProfile(@RequestBody UpdateDoctorProfileDTO updateDoctorProfileDTO)
            throws AccessMismatchException, ItemNotFoundException {
        doctorService.update(IDExtractor.getUserID(), updateDoctorProfileDTO);
        return ResponseEntity.ok("Doctor profile updated successfully");
    }

    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<String> deleteDoctor(@PathVariable String userId) throws ItemNotFoundException {
        doctorService.delete(userId);
        return ResponseEntity.ok("Doctor deleted successfully");
    }

    @GetMapping("/minimal-info/{userId}")
    public ResponseEntity<UserMinimalInfoDTO> updatePatientProfile(@PathVariable String userId) throws ItemNotFoundException {
        return ResponseEntity.ok(doctorService.getUserMinimalInfo(userId));
    }
}

package com.healthcare.patientsdata.controller;

import com.healthcare.patientsdata.exceptions.AccessMismatchException;
import com.healthcare.patientsdata.exceptions.ItemNotFoundException;
import com.healthcare.patientsdata.models.CreateDoctorAccountDTO;
import com.healthcare.patientsdata.models.ReadDoctorPersonalInfoDTO;
import com.healthcare.patientsdata.models.ReadDoctorProfileDTO;
import com.healthcare.patientsdata.models.UpdateDoctorProfileDTO;
import com.healthcare.patientsdata.service.interfaces.DoctorService;
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

    @PutMapping("/{userId}/update")
    public ResponseEntity<String> updateDoctorProfile(@PathVariable String userId, @RequestBody UpdateDoctorProfileDTO updateDoctorProfileDTO)
            throws AccessMismatchException, ItemNotFoundException {
        doctorService.update(userId, updateDoctorProfileDTO);
        return ResponseEntity.ok("Doctor profile updated successfully");
    }

    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<String> deleteDoctor(@PathVariable String userId) throws ItemNotFoundException {
        doctorService.delete(userId);
        return ResponseEntity.ok("Doctor deleted successfully");
    }
}

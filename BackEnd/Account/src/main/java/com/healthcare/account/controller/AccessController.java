package com.healthcare.account.controller;

import com.healthcare.account.exception.AccessDeniedException;
import com.healthcare.account.exception.AccountNotFoundException;
import com.healthcare.account.exception.PasswordMismatchException;
import com.healthcare.account.model.ReadForListDTO;
import com.healthcare.account.model.UserMinimalInfoDTO;
import com.healthcare.account.service.iservice.AccessService;
import com.healthcare.account.model.LoginRequestDTO;
import com.healthcare.account.model.LoginResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountLockedException;
import java.util.List;

// Controller class for handling access-related operations.
@RestController
@RequestMapping("/access")
public class AccessController {

    // Constructor-based injection of AccessService
    private final AccessService accessService;

    public AccessController(AccessService accessService) {
        this.accessService = accessService;
    }

    // Handles user login requests.
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO)
            throws AccountNotFoundException, PasswordMismatchException, AccountLockedException {
        return ResponseEntity.ok(accessService.login(loginRequestDTO));
    }

    // Generates a one-time password (OTP) for the specified identity.
    @PostMapping("/generate-otp/{identity}")
    public ResponseEntity<String> generateOTP(@PathVariable String identity) throws AccountNotFoundException {
        accessService.generateOTP(identity);
        return ResponseEntity.ok("OTP generated successfully.");
    }

    // Generates an internal token for authentication purposes.
    @GetMapping("/generate-internal-token")
    public ResponseEntity<String> generateInternalToken() throws AccessDeniedException {
        String internalToken = accessService.generateInternalToken();
        return ResponseEntity.ok(internalToken);
    }

    // Checks the availability of an email address.
    @GetMapping("/check-email-availability/{email}")
    public ResponseEntity<String> checkEmailAvailability(@PathVariable String email) {
        boolean isAvailable = accessService.checkEmailAvailability(email);
        return ResponseEntity.ok(String.valueOf(isAvailable));
    }

    // Retrieves a list of doctors.
    @GetMapping("/doctors")
    public ResponseEntity<List<ReadForListDTO>> readDoctors(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(accessService.getDoctors(page, size));
    }

    // Retrieves a list of patients.
    @GetMapping("/patients")
    public ResponseEntity<List<ReadForListDTO>> readPatients(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(accessService.getPatients(page, size));
    }

    // Retrieves minimal information about a user.
    @GetMapping("/minimal-info/{userId}")
    public ResponseEntity<UserMinimalInfoDTO> readMinimalInfo(@PathVariable String userId) {
        return ResponseEntity.ok(accessService.getMinimalInfo(userId));
    }

    // Retrieves the email address of a user.
    @GetMapping("/getEmail/{userId}")
    public ResponseEntity<String> getEmail(@PathVariable String userId) {
        return ResponseEntity.ok(accessService.getEmail(userId));
    }
}

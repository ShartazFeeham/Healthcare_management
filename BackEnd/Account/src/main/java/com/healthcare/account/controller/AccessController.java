package com.healthcare.account.controller;

import com.healthcare.account.exception.AccessDeniedException;
import com.healthcare.account.exception.AccountNotFoundException;
import com.healthcare.account.exception.PasswordMismatchException;
import com.healthcare.account.service.iservice.AccessService;
import com.healthcare.account.model.LoginRequestDTO;
import com.healthcare.account.model.LoginResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountLockedException;

@RestController
@RequestMapping("/access")
public class AccessController {

    private final AccessService accessService;

    public AccessController(AccessService accessService) {
        this.accessService = accessService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO)
            throws AccountNotFoundException, PasswordMismatchException, AccountLockedException {
        return ResponseEntity.ok(accessService.login(loginRequestDTO));
    }

    @PostMapping("/generate-otp/{identity}")
    public ResponseEntity<String> generateOTP(@PathVariable String identity) throws AccountNotFoundException {
        accessService.generateOTP(identity);
        return ResponseEntity.ok("OTP generated successfully.");
    }

    @GetMapping("/generate-internal-token")
    public ResponseEntity<String> generateInternalToken() throws AccessDeniedException {
        String internalToken = accessService.generateInternalToken();
        return ResponseEntity.ok(internalToken);
    }

    @GetMapping("/check-email-availability/{email}")
    public ResponseEntity<String> checkEmailAvailability(@PathVariable String email) {
        boolean isAvailable = accessService.checkEmailAvailability(email);
        return ResponseEntity.ok(String.valueOf(isAvailable));
    }
}

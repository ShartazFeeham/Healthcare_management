package com.healthcare.account.controller;

import com.healthcare.account.exception.AccountNotFoundException;
import com.healthcare.account.exception.OTPValidationException;
import com.healthcare.account.exception.PasswordMismatchException;
import com.healthcare.account.service.iservice.RecoveryService;
import com.healthcare.account.model.PasswordChangeDTO;
import com.healthcare.account.model.PasswordResetDTO;
import com.healthcare.account.utilities.token.IDExtractor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recovery")
public class RecoveryController {

    private final RecoveryService recoveryService;

    public RecoveryController(RecoveryService recoveryService) {
        this.recoveryService = recoveryService;
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody PasswordChangeDTO passwordChangeDTO)
            throws AccountNotFoundException, PasswordMismatchException {
        recoveryService.changePassword(IDExtractor.getUserID(), passwordChangeDTO);
        return ResponseEntity.ok("Password changed successfully.");
    }

    @PutMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetDTO passwordResetDTO)
            throws AccountNotFoundException, OTPValidationException {
        recoveryService.resetPassword(passwordResetDTO);
        return ResponseEntity.ok("Password reset successfully.");
    }
}
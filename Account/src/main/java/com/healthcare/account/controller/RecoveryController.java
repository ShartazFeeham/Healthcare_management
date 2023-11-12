package com.healthcare.account.controller;

import com.prep.account.exception.AccountNotFoundException;
import com.prep.account.exception.OTPValidationException;
import com.prep.account.exception.PasswordMismatchException;
import com.prep.account.iservice.RecoveryService;
import com.prep.account.model.PasswordChangeDTO;
import com.prep.account.model.PasswordResetDTO;
import com.prep.account.utilities.token.IDExtractor;
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
        recoveryService.resetPassword(IDExtractor.getUserID(), passwordResetDTO);
        return ResponseEntity.ok("Password reset successfully.");
    }
}
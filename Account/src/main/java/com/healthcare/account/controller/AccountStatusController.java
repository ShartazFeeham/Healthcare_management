package com.healthcare.account.controller;

import com.healthcare.account.exception.AccountNotFoundException;
import com.healthcare.account.service.iservice.AccountStatusService;
import com.healthcare.account.utilities.token.IDExtractor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/status")
public class AccountStatusController {

    private final AccountStatusService accountStatusService;

    public AccountStatusController(AccountStatusService accountStatusService) {
        this.accountStatusService = accountStatusService;
    }

    // User controlled
    @PutMapping("/toggle-two-factor/{status}")
    public ResponseEntity<String> toggleTwoFactor(@PathVariable Boolean status) throws AccountNotFoundException {
        accountStatusService.toggleTwoFactor(IDExtractor.getUserID(), status);
        return ResponseEntity.ok("Two-factor authentication status updated successfully.");
    }

    @PutMapping("/toggle-deactivation/{status}")
    public ResponseEntity<String> toggleDeactivation(@PathVariable Boolean status) throws AccountNotFoundException {
        accountStatusService.toggleDeactivation(IDExtractor.getUserID(), status);
        return ResponseEntity.ok("Account deactivation status updated successfully.");
    }

    // Server controlled
    @PutMapping("/toggle-lockout/{userId}/{status}")
    public ResponseEntity<String> toggleLockout(@PathVariable String userId,
                                                @PathVariable Boolean status) throws AccountNotFoundException {
        accountStatusService.toggleLockout(userId, status);
        return ResponseEntity.ok("Lockout status updated successfully.");
    }

    @PutMapping("/toggle-enabling/{userId}/{status}")
    public ResponseEntity<String> toggleEnabling(@PathVariable String userId,
                                                 @PathVariable Boolean status) throws AccountNotFoundException {
        accountStatusService.toggleEnabling(userId, status);
        return ResponseEntity.ok("Account enabling status updated successfully.");
    }

    @PutMapping("/toggle-suspension/{userId}/{status}")
    public ResponseEntity<String> suspendAccount(@PathVariable String userId, @PathVariable boolean status) throws AccountNotFoundException {
        accountStatusService.suspendAccount(userId, status);
        return ResponseEntity.ok("Account suspension status updated successfully.");
    }

    @PutMapping("/add-ban-hour/{userId}")
    public ResponseEntity<String> addBanHour(@PathVariable String userId, @RequestParam Integer hour)
            throws AccountNotFoundException {
        accountStatusService.addBanHour(userId, hour);
        return ResponseEntity.ok("Ban hours added successfully.");
    }
}
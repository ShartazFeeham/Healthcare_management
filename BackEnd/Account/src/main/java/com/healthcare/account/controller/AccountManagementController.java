package com.healthcare.account.controller;

import com.healthcare.account.exception.AccountNotFoundException;
import com.healthcare.account.model.AdminAccountCreateDTO;
import com.healthcare.account.service.iservice.AccountManagementService;
import com.healthcare.account.model.AccountCreateDTO;
import com.healthcare.account.model.AccountReadDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountManagementController {

    private final AccountManagementService accountManagementService;

    // Constructor-based injection of AccountManagementService
    public AccountManagementController(AccountManagementService accountManagementService) {
        this.accountManagementService = accountManagementService;
    }

    // Handles the creation of a user account.
    @PostMapping("/create-account")
    public ResponseEntity<String> createAccount(@RequestBody AccountCreateDTO accountCreateDTO) {
        accountManagementService.createAccount(accountCreateDTO);
        return ResponseEntity.ok("Account created successfully.");
    }

    // Handles the creation of an admin account.
    @PostMapping("/create-admin-account")
    public ResponseEntity<String> createAdminAccount(@RequestBody AdminAccountCreateDTO adminCreateDTO) {
        accountManagementService.createAdminAccount(adminCreateDTO);
        return ResponseEntity.ok("New Admin account created successfully.");
    }

    // Retrieves information about a user.
    @GetMapping("/get-user-info/{identity}")
    public ResponseEntity<AccountReadDTO> getUserInfo(@PathVariable String identity) throws AccountNotFoundException {
        AccountReadDTO userInfo = accountManagementService.getUserInfo(identity);
        return ResponseEntity.ok(userInfo);
    }
}

package com.healthcare.account.service;

import com.healthcare.account.entity.Account;
import com.healthcare.account.entity.Role;
import com.healthcare.account.exception.AccountNotFoundException;
import com.healthcare.account.exception.DuplicateEntityException;
import com.healthcare.account.service.iservice.AccessService;
import com.healthcare.account.service.iservice.AccountManagementService;
import com.healthcare.account.model.AccountCreateDTO;
import com.healthcare.account.model.AccountReadDTO;
import com.healthcare.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service @RequiredArgsConstructor
public class AccountManagementServiceImpl implements AccountManagementService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessService accessService;

    @Override
    public AccountReadDTO createAccount(AccountCreateDTO accountCreateDTO) throws DuplicateEntityException {
        // Check if the email  already exists to prevent duplicates
        if (accountRepository.findByEmail(accountCreateDTO.getEmail()).isPresent()) {
            throw new DuplicateEntityException("Account", "email", accountCreateDTO.getEmail());
        }

        // Create a new account entity and save it to the database
        Account newAccount = new Account();
        newAccount.setUserId(accountCreateDTO.getUserId());
        newAccount.setEmail(accountCreateDTO.getEmail());
        newAccount.setPassword(passwordEncoder.encode(accountCreateDTO.getPassword()));
        newAccount.setRegisterDate(LocalDate.now());
        newAccount.setAccountLocked(true);

        if(newAccount.getUserId().startsWith("P")) newAccount.setRole(Role.PATIENT);
        else if(newAccount.getUserId().startsWith("D")) newAccount.setRole(Role.DOCTOR);
        else newAccount.setRole(Role.ADMIN);

        accountRepository.save(newAccount);
        accessService.generateOTP(newAccount.getUserId());

        return getUserInfo(newAccount.getUserId());
    }

    @Override
    public AccountReadDTO getUserInfo(String identity) throws AccountNotFoundException {
        Account account = accessService.findByIdentity(identity);

        AccountReadDTO result = new AccountReadDTO();
        result.setEmail(account.getEmail());
        result.setUserId(account.getUserId());
        result.setRegisterDate(account.getRegisterDate());
        result.setRole(account.getRole().toString());

        return result;
    }
}

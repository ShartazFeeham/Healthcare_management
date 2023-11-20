package com.healthcare.account.service;

import com.healthcare.account.entity.Account;
import com.healthcare.account.entity.AdminAccount;
import com.healthcare.account.entity.Role;
import com.healthcare.account.exception.AccountNotFoundException;
import com.healthcare.account.exception.CustomException;
import com.healthcare.account.exception.DuplicateEntityException;
import com.healthcare.account.model.AdminAccountCreateDTO;
import com.healthcare.account.repository.AdminAccountRepository;
import com.healthcare.account.service.iservice.AccessService;
import com.healthcare.account.service.iservice.AccountManagementService;
import com.healthcare.account.model.AccountCreateDTO;
import com.healthcare.account.model.AccountReadDTO;
import com.healthcare.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service @RequiredArgsConstructor
public class AccountManagementServiceImpl implements AccountManagementService {

    private final AdminAccountRepository adminAccountRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessService accessService;

    @Override
    public void createAccount(AccountCreateDTO accountCreateDTO) throws DuplicateEntityException {
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
        if(accountCreateDTO.getUserId().startsWith("D")){
            newAccount.setAccountDeactivated(true);
        }

        if(newAccount.getUserId().startsWith("P")) newAccount.setRole(Role.PATIENT);
        else if(newAccount.getUserId().startsWith("D")) newAccount.setRole(Role.DOCTOR);
        else if(newAccount.getUserId().startsWith("A")) newAccount.setRole(Role.ADMIN);
        else throw new CustomException("ItemNotFoundException", "Account", "Given role doesn't exist!", HttpStatus.NOT_FOUND);

        accountRepository.save(newAccount);
        accessService.generateOTP(newAccount.getUserId());

        getUserInfo(newAccount.getUserId());
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

    @Override
    public void createAdminAccount(AdminAccountCreateDTO createAdminDTO) throws DuplicateEntityException {
        String id = getId(createAdminDTO.getFirstName(), createAdminDTO.getLastName());
        AdminAccount adminAccount = new AdminAccount();
        adminAccount.setUserId(id);

        adminAccount.setFirstName(createAdminDTO.getFirstName());
        adminAccount.setLastName(createAdminDTO.getLastName());

        AccountCreateDTO account = new AccountCreateDTO();
        account.setUserId(id);
        account.setEmail(createAdminDTO.getEmail());
        account.setPassword(createAdminDTO.getPassword());
        
        adminAccountRepository.save(adminAccount);
        
        try{
            createAccount(account);
        }
        catch (Exception e){
            adminAccountRepository.deleteById(id);
            throw e;
        }
    }

    private String getId(String firstName, String lastName) {
        // Generate the initial ID pattern using the first letters of the first and last name
        String idPattern = "A" + firstName.toUpperCase().charAt(0) + lastName.toUpperCase().charAt(0);
        long count = adminAccountRepository.countByUserIdStartingWith(idPattern) + 1;
        return  idPattern + count;
    }
}

package com.healthcare.account.service.iservice;

import com.healthcare.account.entity.Account;
import com.healthcare.account.exception.AccessDeniedException;
import com.healthcare.account.exception.AccountNotFoundException;
import com.healthcare.account.exception.PasswordMismatchException;
import com.healthcare.account.model.LoginRequestDTO;
import com.healthcare.account.model.LoginResponseDTO;

import javax.security.auth.login.AccountLockedException;

public interface AccessService {
    Account findByIdentity(String identity);
    LoginResponseDTO login(LoginRequestDTO loginDTO) throws AccountNotFoundException, PasswordMismatchException, AccountLockedException;
    void generateOTP(String identity) throws AccountNotFoundException;
    String generateInternalToken() throws AccessDeniedException;
    Boolean checkEmailAvailability(String email);
}

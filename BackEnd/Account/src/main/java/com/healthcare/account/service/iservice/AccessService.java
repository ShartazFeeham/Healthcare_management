package com.healthcare.account.service.iservice;

import com.healthcare.account.entity.Account;
import com.healthcare.account.exception.AccessDeniedException;
import com.healthcare.account.exception.AccountNotFoundException;
import com.healthcare.account.exception.PasswordMismatchException;
import com.healthcare.account.model.ReadForListDTO;
import com.healthcare.account.model.LoginRequestDTO;
import com.healthcare.account.model.LoginResponseDTO;
import com.healthcare.account.model.UserMinimalInfoDTO;

import javax.security.auth.login.AccountLockedException;
import java.util.List;

public interface AccessService {
    Account findByIdentity(String identity);
    LoginResponseDTO login(LoginRequestDTO loginDTO) throws AccountNotFoundException, PasswordMismatchException, AccountLockedException;
    void generateOTP(String identity) throws AccountNotFoundException;
    String generateInternalToken() throws AccessDeniedException;
    Boolean checkEmailAvailability(String email);
    public List<ReadForListDTO> getDoctors(int page, int size);
    public List<ReadForListDTO> getPatients(int page, int size);
    UserMinimalInfoDTO getMinimalInfo(String userId);
}

package com.healthcare.account.service;

import com.healthcare.account.entity.Account;
import com.healthcare.account.exception.AccountNotFoundException;
import com.healthcare.account.exception.OTPValidationException;
import com.healthcare.account.exception.PasswordMismatchException;
import com.healthcare.account.service.iservice.RecoveryService;
import com.healthcare.account.model.PasswordChangeDTO;
import com.healthcare.account.model.PasswordResetDTO;
import com.healthcare.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service @RequiredArgsConstructor
public class RecoveryServiceImpl implements RecoveryService {

    private final AccountRepository accountRepository;

    @Override
    public void changePassword(String userId, PasswordChangeDTO passwordChangeDTO)
            throws AccountNotFoundException, PasswordMismatchException {
        // Check if the account exists
        Account account = accountRepository.findById(userId)
                .orElseThrow(() -> new AccountNotFoundException("user ID " + userId));

        // Check if the old password matches the existing password
        if (!passwordChangeDTO.getOldPassword().equals(account.getPassword())) {
            throw new PasswordMismatchException();
        }

        // Update the password
        account.setPassword(passwordChangeDTO.getNewPassword());
        accountRepository.save(account);
    }

    @Override
    public void resetPassword(PasswordResetDTO passwordResetDTO) throws AccountNotFoundException {
        // Check if the account exists
        Account account = accountRepository.findByEmail(passwordResetDTO.getEmail())
                .orElseThrow(() -> new AccountNotFoundException("email " + passwordResetDTO.getEmail()));

        if(account.getOtp() != null && account.getOtp() != 0
                && account.getOtp().equals(passwordResetDTO.getOtp())
                && account.getOtpGenerationTime().isAfter(LocalDateTime.now().minusMinutes(5L))){
            account.setPassword(passwordResetDTO.getNewPassword());
            accountRepository.save(account);
        }
        else throw new OTPValidationException(passwordResetDTO.getOtp());
    }
}
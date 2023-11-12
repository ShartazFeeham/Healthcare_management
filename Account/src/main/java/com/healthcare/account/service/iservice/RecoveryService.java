package com.healthcare.account.service.iservice;

import com.healthcare.account.exception.AccountNotFoundException;
import com.healthcare.account.exception.PasswordMismatchException;
import com.healthcare.account.model.PasswordChangeDTO;
import com.healthcare.account.model.PasswordResetDTO;

import java.util.UUID;

public interface RecoveryService {
    void changePassword(String userId, PasswordChangeDTO passwordChangeDTO) throws AccountNotFoundException, PasswordMismatchException;
    void resetPassword(PasswordResetDTO passwordResetDTO) throws AccountNotFoundException;
}

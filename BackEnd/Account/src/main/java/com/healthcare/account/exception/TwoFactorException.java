package com.healthcare.account.exception;

import org.springframework.http.HttpStatus;

public class TwoFactorException extends CustomException {
    public TwoFactorException(String email) {
        super("TwoFactorException", "Account", "An OTP has been send to your email '" + email + "', please use the OTP to login.", HttpStatus.LOCKED);
    }
}
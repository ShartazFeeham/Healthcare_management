package com.healthcare.account.exception;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class AccountLockoutException extends CustomException {
    public AccountLockoutException() {
        super("AccountLockoutException", "Account", "Your account is currently locked, verify your email to unlock it.", HttpStatus.NOT_ACCEPTABLE);
    }
    public AccountLockoutException(String message){
        super("AccountLockoutException", "Account", message, HttpStatus.FORBIDDEN);
    }
}
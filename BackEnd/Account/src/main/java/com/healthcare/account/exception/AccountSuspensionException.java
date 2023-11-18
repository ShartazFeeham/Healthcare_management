package com.healthcare.account.exception;

import org.springframework.http.HttpStatus;

public class AccountSuspensionException  extends CustomException {
    public AccountSuspensionException () {
        super("AccountSuspensionException", "Account", "This account has been suspended for valid reason.", HttpStatus.NOT_ACCEPTABLE);
    }
}
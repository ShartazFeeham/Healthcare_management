package com.healthcare.account.exception;

import org.springframework.http.HttpStatus;

public class AccountBanException extends CustomException {
    public AccountBanException(Integer time) {
        super("AccountBanException", "Account", "Can not perform operation due to account ban. Account will unban after " + time + " hours.", HttpStatus.NOT_ACCEPTABLE);
    }
}
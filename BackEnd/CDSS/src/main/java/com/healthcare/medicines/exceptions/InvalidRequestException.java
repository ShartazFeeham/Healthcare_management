package com.healthcare.medicines.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidRequestException extends CustomException{
    public InvalidRequestException(String message) {
        super("InvalidRequestException", message, HttpStatus.BAD_REQUEST);
    }
}

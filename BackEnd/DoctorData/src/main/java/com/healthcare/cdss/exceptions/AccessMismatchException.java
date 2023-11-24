package com.healthcare.cdss.exceptions;

import org.springframework.http.HttpStatus;

public class AccessMismatchException extends CustomException{
    public AccessMismatchException(String message){
        super("AccessMismatchException", message, HttpStatus.BAD_REQUEST);
    }

    public AccessMismatchException(String message, HttpStatus status){
        super("AccessMismatchException", message, status);
    }
}

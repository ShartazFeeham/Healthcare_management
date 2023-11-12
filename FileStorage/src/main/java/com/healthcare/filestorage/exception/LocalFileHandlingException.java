package com.healthcare.filestorage.exception;

import org.springframework.http.HttpStatus;

public class LocalFileHandlingException extends CustomException{
    public LocalFileHandlingException(String message) {
        super("LocalFileHandlingException", message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

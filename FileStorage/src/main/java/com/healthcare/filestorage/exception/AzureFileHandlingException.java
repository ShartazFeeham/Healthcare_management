package com.healthcare.filestorage.exception;

import org.springframework.http.HttpStatus;

public class AzureFileHandlingException extends CustomException{
    public AzureFileHandlingException(String message) {
        super("AzureFileHandlingException", message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

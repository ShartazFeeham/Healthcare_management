package com.healtcare.community.exception;

import org.springframework.http.HttpStatus;

public class InternalCommunicationException extends CustomException {
    public InternalCommunicationException(String message) {
        super("InternalCommunicationException", message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

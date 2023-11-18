package com.healthcare.notification.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@Getter @Setter @AllArgsConstructor
public class CustomException extends IOException {
    private final String exceptionName;
    private final String message;
    private final HttpStatus httpStatus;
}
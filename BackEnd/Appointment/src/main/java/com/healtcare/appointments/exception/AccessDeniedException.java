package com.healtcare.appointments.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class AccessDeniedException extends CustomException {
    public AccessDeniedException(String message) {
        super("AccessDeniedException", message, HttpStatus.NOT_ACCEPTABLE);
    }
}
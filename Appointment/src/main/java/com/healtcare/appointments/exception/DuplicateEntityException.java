package com.healtcare.appointments.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class DuplicateEntityException extends CustomException {
    public DuplicateEntityException(String entityType, String parameter, String value) {
        super("DuplicateEntityException", "An " + entityType + " with " + parameter + " " + value + " already exists.",
                HttpStatus.BAD_REQUEST);
    }
}
package com.healthcare.medicines.exceptions;

import org.springframework.http.HttpStatus;

public class DuplicateEntityException extends CustomException{
    public DuplicateEntityException(String entityName, String entityId) {
        super("DuplicateEntityException",
                "A record of " + entityName + " with ID " + entityId + " already exists!",
                HttpStatus.BAD_REQUEST);
    }
}

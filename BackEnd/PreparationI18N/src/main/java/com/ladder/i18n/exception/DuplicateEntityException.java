package com.ladder.i18n.exception;

import org.springframework.http.HttpStatus;

public class DuplicateEntityException extends CustomException{
    public DuplicateEntityException(String entityName, String entityId) {
        super("DuplicateEntityException", "Duplicate entry", "Attempt to create an item",
                entityName + " with id " + entityId + " already exists..", HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
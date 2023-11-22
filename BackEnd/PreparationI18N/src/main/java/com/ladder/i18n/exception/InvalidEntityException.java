package com.ladder.i18n.exception;

import org.springframework.http.HttpStatus;

public class InvalidEntityException extends CustomException{
    public InvalidEntityException(String entityName, String entityId) {
        super("InvalidEntityException", "Invalid entry", "Attempt to create an item",
                entityName + " with id " + entityId + " already exists..", HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
package com.healthcare.notification.exceptions;

import org.springframework.http.HttpStatus;

public class ItemNotFoundException extends CustomException{
    public ItemNotFoundException(String entityName, String entityId) {
        super("ItemNotFoundException",
                "The record " + entityName + " with ID " + entityId + " was not found!",
                HttpStatus.NOT_FOUND);
    }
}

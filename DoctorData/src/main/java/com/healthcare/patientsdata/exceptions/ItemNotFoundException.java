package com.healthcare.patientsdata.exceptions;

import org.springframework.http.HttpStatus;

public class ItemNotFoundException extends CustomException{
    public ItemNotFoundException(String entityName, String entityId) {
        super("ItemNotFoundException",
                "A record of " + entityName + " with ID " + entityId + " was not found!",
                HttpStatus.NOT_FOUND);
    }
}

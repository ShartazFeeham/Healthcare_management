package com.ladder.i18n.exception;

import org.springframework.http.HttpStatus;

public class LocalizedResourceNotFoundException extends CustomException{
    public LocalizedResourceNotFoundException(String resourceId) {
        super("LocalizedResourceNotFoundException", "Item not found", "Search for a localized resource",
                "Localized resource with id " + resourceId + " was not found.", HttpStatus.NOT_FOUND);
    }
}

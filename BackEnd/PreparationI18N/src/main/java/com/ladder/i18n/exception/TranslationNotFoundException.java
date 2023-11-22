package com.ladder.i18n.exception;

import org.springframework.http.HttpStatus;

public class TranslationNotFoundException extends CustomException{
    public TranslationNotFoundException(Long translationId) {
        super("TranslationNotFoundException", "Item not found", "Search for a translation",
                "Translation resource with id " + translationId + " was not found.", HttpStatus.NOT_FOUND);
    }
}
package com.ladder.i18n.exception;

import org.springframework.http.HttpStatus;

public class LanguageNotFoundException extends CustomException {
    public LanguageNotFoundException(Long languageId) {
        super("LanguageNotFoundException", "Language not found", "Search for a language",
                "Language resource with ID " + languageId + " was not found.", HttpStatus.NOT_FOUND);
    }
}

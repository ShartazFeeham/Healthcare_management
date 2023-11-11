package com.healthcare.integration.exception;

import com.ladder.IntegrationService.model.TranslationRequest;
import org.springframework.http.HttpStatus;

public class GoogleTranslatorException extends CustomException{
    public GoogleTranslatorException(TranslationRequest translationRequest) {
        super("GoogleTranslatorException",
                "External service",
                "Translating a text through google translator", "Failed to translate \""
                + (translationRequest.getText().length() > 10 ? translationRequest.getText().substring(0, 10) : translationRequest.getText())
                + " from " + translationRequest.getFrom() + " to " + translationRequest.getTo(),
                HttpStatus.BAD_GATEWAY);
    }
}

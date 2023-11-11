package com.healthcare.integration.interfaces;

import com.ladder.IntegrationService.exception.GoogleTranslatorException;
import com.ladder.IntegrationService.model.LanguageTag;
import com.ladder.IntegrationService.model.TranslationRequest;

import java.util.List;

public interface TranslationService {
    public String translate(TranslationRequest translationRequest) throws GoogleTranslatorException;
    public List<LanguageTag> getAvailableLanguageTags();
}

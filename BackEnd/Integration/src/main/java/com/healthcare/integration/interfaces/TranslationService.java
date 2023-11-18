package com.healthcare.integration.interfaces;

import com.healthcare.integration.exception.GoogleTranslatorException;
import com.healthcare.integration.model.LanguageTag;
import com.healthcare.integration.model.TranslationRequest;

import java.util.List;

public interface TranslationService {
    public String translate(TranslationRequest translationRequest) throws GoogleTranslatorException;
    public List<LanguageTag> getAvailableLanguageTags();
}

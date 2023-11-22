package com.ladder.i18n.utility.constants;

import com.ladder.i18n.entity.Language;
import com.ladder.i18n.service.definition.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class TranslationConstants {
    // The base URL of the translation API
    public static final String TRANSLATION_API_BASE_URL = "http://localhost:5300/v1/translation/translate";
    public static final Map<String, String> LANGUAGE_MAP = new HashMap<>();

    static {
        // Add language code and name mappings to the map
        LANGUAGE_MAP.put("zh", "Chinese");
        LANGUAGE_MAP.put("es", "Spanish");
        LANGUAGE_MAP.put("en", "English");
        LANGUAGE_MAP.put("hi", "Hindi");
        LANGUAGE_MAP.put("ar", "Arabic");
        LANGUAGE_MAP.put("bn", "Bengali");
        LANGUAGE_MAP.put("pt", "Portuguese");
        LANGUAGE_MAP.put("ru", "Russian");
        LANGUAGE_MAP.put("ja", "Japanese");
        LANGUAGE_MAP.put("de", "German");
    }
}
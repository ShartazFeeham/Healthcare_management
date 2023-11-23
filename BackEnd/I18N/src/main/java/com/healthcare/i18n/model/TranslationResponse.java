package com.healthcare.i18n.model;

import com.healthcare.i18n.entity.Translation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class TranslationResponse {

    private String translatedText;

    public Translation toTranslation(String languageCode, String languageName) {
        Translation translation = new Translation();
        translation.setLanguageCode(languageCode);
        translation.setLocalizedText(translatedText);
        translation.setLanguageName(languageName);
        return translation;
    }
}

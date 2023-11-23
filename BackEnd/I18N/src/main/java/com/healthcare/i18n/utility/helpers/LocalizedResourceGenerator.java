package com.healthcare.i18n.utility.helpers;

import com.healthcare.i18n.entity.Language;
import com.healthcare.i18n.entity.LocalizedResource;
import com.healthcare.i18n.entity.Translation;
import com.healthcare.i18n.model.TranslationRequest;
import com.healthcare.i18n.utility.constants.TranslationConstants;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class LocalizedResourceGenerator {

    public static List<Translation> generateTranslations(LocalizedResource resource, String alternative, List<Language> languages) {

        List<Translation> translations = new ArrayList<>();

        for (Language language : languages) {
            WebClient webClient = WebClient.create("");
            String toLanguage = language.getLanguageCode();
            // Make a POST request to the translation API
            String result = webClient.post()
                    .uri(TranslationConstants.TRANSLATION_API_BASE_URL)
                    .body(Mono.just(
                            new TranslationRequest("en", toLanguage, alternative)
                    ), TranslationRequest.class)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            Translation translation = new Translation();
            translation.setLocalizedText(result);
            translation.setLanguageCode(language.getLanguageCode());
            translation.setLanguageName(language.getLanguageName());
            translation.setParentResource(resource);

            translations.add(translation);
        }

        return translations;
    }
}

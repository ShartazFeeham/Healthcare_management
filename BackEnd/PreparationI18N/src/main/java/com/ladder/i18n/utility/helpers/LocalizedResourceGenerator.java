package com.ladder.i18n.utility.helpers;

import com.ladder.i18n.entity.Language;
import com.ladder.i18n.entity.LocalizedResource;
import com.ladder.i18n.entity.Translation;
import com.ladder.i18n.model.TranslationRequest;
import com.ladder.i18n.model.TranslationResponse;
import com.ladder.i18n.service.definition.LanguageService;
import com.ladder.i18n.utility.constants.TranslationConstants;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
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

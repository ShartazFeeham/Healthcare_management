package com.healthcare.integration.service;

import com.healthcare.integration.exception.GoogleTranslatorException;
import com.healthcare.integration.interfaces.TranslationService;
import com.healthcare.integration.model.LanguageTag;
import com.healthcare.integration.model.TranslationRequest;
import com.healthcare.integration.utility.helper.GoogleTranslate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TranslationServiceImpl implements TranslationService {
    @Override
    public String translate(TranslationRequest translationRequest) throws GoogleTranslatorException {
        try{
            String result = GoogleTranslate.translate(translationRequest.getFrom(), translationRequest.getTo(), translationRequest.getText());
            return result;
        }
        catch (IOException ioe){
            throw new GoogleTranslatorException(translationRequest);
        }
    }

    @Override
    public List<LanguageTag> getAvailableLanguageTags() {
        List<LanguageTag> languageTags = new ArrayList<>();

        languageTags.add(new LanguageTag("Afrikaans", "af"));
        languageTags.add(new LanguageTag("Arabic", "ar"));
        languageTags.add(new LanguageTag("Bengali", "bn"));
        languageTags.add(new LanguageTag("Bulgarian", "bg"));
        languageTags.add(new LanguageTag("Chinese (Simplified)", "zh"));
        languageTags.add(new LanguageTag("Czech", "cs"));
        languageTags.add(new LanguageTag("Danish", "da"));
        languageTags.add(new LanguageTag("Dutch", "nl"));
        languageTags.add(new LanguageTag("English", "en"));
        languageTags.add(new LanguageTag("Finnish", "fi"));
        languageTags.add(new LanguageTag("French", "fr"));
        languageTags.add(new LanguageTag("German", "de"));
        languageTags.add(new LanguageTag("Greek", "el"));
        languageTags.add(new LanguageTag("Hebrew", "he"));
        languageTags.add(new LanguageTag("Hindi", "hi"));
        languageTags.add(new LanguageTag("Hungarian", "hu"));
        languageTags.add(new LanguageTag("Icelandic", "is"));
        languageTags.add(new LanguageTag("Indonesian", "id"));
        languageTags.add(new LanguageTag("Italian", "it"));
        languageTags.add(new LanguageTag("Japanese", "ja"));
        languageTags.add(new LanguageTag("Korean", "ko"));
        languageTags.add(new LanguageTag("Malay", "ms"));
        languageTags.add(new LanguageTag("Norwegian", "no"));
        languageTags.add(new LanguageTag("Polish", "pl"));
        languageTags.add(new LanguageTag("Portuguese", "pt"));
        languageTags.add(new LanguageTag("Russian", "ru"));
        languageTags.add(new LanguageTag("Spanish", "es"));
        languageTags.add(new LanguageTag("Swedish", "sv"));
        languageTags.add(new LanguageTag("Tamil", "ta"));
        languageTags.add(new LanguageTag("Turkish", "tr"));
        languageTags.add(new LanguageTag("Ukrainian", "uk"));
        languageTags.add(new LanguageTag("Urdu", "ur"));

        return languageTags;
    }
}

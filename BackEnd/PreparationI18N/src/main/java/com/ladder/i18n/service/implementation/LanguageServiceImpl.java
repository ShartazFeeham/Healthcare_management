package com.ladder.i18n.service.implementation;

import com.ladder.i18n.entity.Language;
import com.ladder.i18n.exception.DuplicateEntityException;
import com.ladder.i18n.exception.LanguageNotFoundException;
import com.ladder.i18n.model.LanguageDTO;
import com.ladder.i18n.repository.LanguageRepository;
import com.ladder.i18n.service.definition.LanguageService;
import com.ladder.i18n.utility.constants.TranslationConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;
    private List<Language> availableLanguages = new ArrayList<>();

    @Override
    public void stackAvailableLanguages() {
        availableLanguages = readAll();
    }

    @Override
    public List<Language> getStackedAvailableLanguages() {
        if(availableLanguages.isEmpty()) {
            stackAvailableLanguages();
        }
        return availableLanguages;
    }

    @Override
    public void create(LanguageDTO languageDTO) throws DuplicateEntityException {
        // Check for duplicate entry
        if (languageRepository.existsByLanguageCode(languageDTO.getLanguageCode())
                || languageRepository.existsByLanguageName(languageDTO.getLanguageName())) {
            throw new DuplicateEntityException("Language", languageDTO.getLanguageCode()+"/"+languageDTO.getLanguageName());
        }

        Language language = new Language();
        language.setLanguageCode(languageDTO.getLanguageCode().toLowerCase());
        language.setLanguageName(languageDTO.getLanguageName().substring(0, 1).toUpperCase()
                + languageDTO.getLanguageName().substring(1).toLowerCase());
        languageRepository.save(language);
    }

    @Override
    public List<Language> readAll() {
        List<Language> languages = languageRepository.findAll();
        if(languages.isEmpty()){
            for(Map.Entry<String, String> set: TranslationConstants.LANGUAGE_MAP.entrySet()){
                LanguageDTO language = new LanguageDTO();
                language.setLanguageCode(set.getKey());
                language.setLanguageName(set.getValue());

                try{
                    create(language);
                }
                catch (DuplicateEntityException dee){
                    // ... Nothing to do.
                }
            }
        }
        return languageRepository.findAll();
    }

    @Override
    public Language readById(Long languageId) throws LanguageNotFoundException {
        return languageRepository.findById(languageId) .orElseThrow(() -> new LanguageNotFoundException(languageId));
    }

    @Override
    public void update(Long languageId, LanguageDTO languageDTO) throws DuplicateEntityException, LanguageNotFoundException {
        Language existingLanguage = languageRepository.findById(languageId)
                .orElseThrow(() -> new LanguageNotFoundException(languageId));

        String newLanguageCode = languageDTO.getLanguageCode();

        // Check for duplicate entry
        if (languageRepository.existsByLanguageCodeAndLanguageIdNot(newLanguageCode, languageId)) {
            throw new DuplicateEntityException("Language", newLanguageCode);
        }

        existingLanguage.setLanguageCode(newLanguageCode);
        existingLanguage.setLanguageName(languageDTO.getLanguageName());
        languageRepository.save(existingLanguage);
    }

    @Override
    public void delete(Long languageId) throws LanguageNotFoundException {
        if (!languageRepository.existsById(languageId)) {
            throw new LanguageNotFoundException(languageId);
        }
        languageRepository.deleteById(languageId);
    }
}

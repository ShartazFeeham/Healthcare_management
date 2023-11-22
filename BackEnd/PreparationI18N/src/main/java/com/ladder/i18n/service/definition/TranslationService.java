package com.ladder.i18n.service.definition;

import com.ladder.i18n.entity.Translation;
import com.ladder.i18n.exception.DuplicateEntityException;
import com.ladder.i18n.exception.LocalizedResourceNotFoundException;
import com.ladder.i18n.exception.TranslationNotFoundException;
import com.ladder.i18n.model.TranslationDTO;

import java.util.List;

public interface TranslationService {
    Translation create(TranslationDTO translationDTO) throws LocalizedResourceNotFoundException, DuplicateEntityException;
    Translation update(Long translationId, TranslationDTO translationDTO) throws TranslationNotFoundException;
    Translation getById(Long translationId) throws TranslationNotFoundException;
    void delete(Long translationId) throws TranslationNotFoundException;
    List<Translation> getAllTranslations();
    List<Translation> getByPagination(int itemPerPage, int pageNo);
    List<Translation> getByLocalizedResource(String resourceId);
    List<Translation> getTranslationsForLanguage(String languageCode);
    List<Translation> getTranslationsByLanguageName(String languageName);
}

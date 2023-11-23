package com.healthcare.i18n.service.definition;

import com.healthcare.i18n.entity.Language;
import com.healthcare.i18n.model.LanguageDTO;
import com.healthcare.i18n.exception.DuplicateEntityException;
import com.healthcare.i18n.exception.LanguageNotFoundException;

import java.util.List;

public interface LanguageService {
    public void stackAvailableLanguages();
    public List<Language> getStackedAvailableLanguages();
    public void create(LanguageDTO languageDTO) throws DuplicateEntityException;
    public List<Language> readAll();
    public Language readById(Long languageId) throws LanguageNotFoundException;
    public void update(Long languageId, LanguageDTO languageDTO) throws DuplicateEntityException, LanguageNotFoundException;
    public void delete(Long languageId) throws LanguageNotFoundException;
}

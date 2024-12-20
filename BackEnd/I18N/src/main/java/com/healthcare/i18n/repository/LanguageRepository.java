package com.healthcare.i18n.repository;

import com.healthcare.i18n.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    boolean existsByLanguageCode(String languageCode);
    boolean existsByLanguageCodeAndLanguageIdNot(String newLanguageCode, Long languageId);
    boolean existsByLanguageName(String languageName);
}

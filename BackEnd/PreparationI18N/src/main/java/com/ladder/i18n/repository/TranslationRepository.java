package com.ladder.i18n.repository;

import com.ladder.i18n.entity.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, Long> {
    List<Translation> findByParentResourceResourceId(String resourceId);
    List<Translation> findByLanguageCode(String languageCode);
    List<Translation> findByLanguageName(String languageName);
}
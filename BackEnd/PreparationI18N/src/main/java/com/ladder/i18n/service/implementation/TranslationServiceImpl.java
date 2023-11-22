package com.ladder.i18n.service.implementation;

import com.ladder.i18n.entity.Translation;
import com.ladder.i18n.exception.DuplicateEntityException;
import com.ladder.i18n.exception.LocalizedResourceNotFoundException;
import com.ladder.i18n.exception.TranslationNotFoundException;
import com.ladder.i18n.model.TranslationDTO;
import com.ladder.i18n.repository.TranslationRepository;
import com.ladder.i18n.service.definition.LocalizedResourceService;
import com.ladder.i18n.service.definition.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class TranslationServiceImpl implements TranslationService {

    private final TranslationRepository translationRepository;
    private final LocalizedResourceService localizedResourceService;

    @Override
    public Translation create(TranslationDTO translationDTO) throws LocalizedResourceNotFoundException, DuplicateEntityException {

        if(getByLocalizedResource(translationDTO.getResourceId())
                .stream().anyMatch(t -> t.getLanguageCode().equals(translationDTO.getLanguageCode()))){
            throw new DuplicateEntityException("Translation", translationDTO.getResourceId() + "[" + translationDTO.getLanguageName() + "]");
        }

        Translation translation = new Translation();

        translation.setLanguageCode(translationDTO.getLanguageCode());
        translation.setLocalizedText(translationDTO.getLocalizedText());
        translation.setParentResource(localizedResourceService.getById(translationDTO.getResourceId()));
        translation.setLanguageName(translationDTO.getLanguageName());

        return translationRepository.save(translation);
    }

    @Override
    public Translation update(Long translationId, TranslationDTO translationDTO) throws TranslationNotFoundException {

        Translation translation = getById(translationId);

        translation.setLanguageCode(translationDTO.getLanguageCode());
        translation.setLocalizedText(translationDTO.getLocalizedText());
        translation.setLanguageName(translationDTO.getLanguageName());

        return translationRepository.save(translation);
    }

    @Override
    public Translation getById(Long translationId) throws TranslationNotFoundException {
        Translation translation = translationRepository.findById(translationId).orElse(null);
        if(translation == null) throw new TranslationNotFoundException(translationId);
        return translation;
    }

    @Override
    public void delete(Long translationId) throws TranslationNotFoundException {
        getById(translationId);
        translationRepository.deleteById(translationId);
    }

    @Override
    public List<Translation> getAllTranslations() {
        return translationRepository.findAll();
    }

    @Override
    public List<Translation> getByPagination(int itemPerPage, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, itemPerPage);
        Page<Translation> translationPage = translationRepository.findAll(pageable);
        return translationPage.getContent();
    }

    @Override
    public List<Translation> getByLocalizedResource(String resourceId) {
        return translationRepository.findByParentResourceResourceId(resourceId);
    }

    @Override
    public List<Translation> getTranslationsForLanguage(String languageCode) {
        return translationRepository.findByLanguageCode(languageCode);
    }

    @Override
    public List<Translation> getTranslationsByLanguageName(String languageName) {
        return translationRepository.findByLanguageName(languageName);
    }
}

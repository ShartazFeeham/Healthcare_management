package com.healthcare.i18n.service.implementation;

import com.healthcare.i18n.entity.LocalizedResource;
import com.healthcare.i18n.entity.Translation;
import com.healthcare.i18n.model.LocalizedResourceDTO;
import com.healthcare.i18n.repository.LocalizedResourceRepository;
import com.healthcare.i18n.service.definition.LanguageService;
import com.healthcare.i18n.utility.helpers.LocalizedResourceGenerator;
import com.healthcare.i18n.exception.DuplicateEntityException;
import com.healthcare.i18n.exception.InvalidEntityException;
import com.healthcare.i18n.exception.LocalizedResourceNotFoundException;
import com.healthcare.i18n.service.definition.LocalizedResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service @RequiredArgsConstructor
public class LocalizedResourceServiceImpl implements LocalizedResourceService {

    private final LocalizedResourceRepository localizedResourceRepository;
    private final LanguageService languageService;

    @Override
    public LocalizedResource create(LocalizedResourceDTO localizedResourceDTO) throws DuplicateEntityException, InvalidEntityException {

        if(localizedResourceRepository.findById(localizedResourceDTO.getResourceId()).orElse(null) != null) {
            throw new DuplicateEntityException("Localized Resource", localizedResourceDTO.getResourceId());
        }

        if(localizedResourceDTO.getResourceId() == null || localizedResourceDTO.getResourceId().length() < 5) {
            throw new InvalidEntityException("Localized Resource", localizedResourceDTO.getResourceId());
        }

        LocalizedResource localizedResource = new LocalizedResource();

        localizedResource.setResourceId(localizedResourceDTO.getResourceId());
        localizedResource.setCreatedBy(localizedResourceDTO.getCreatedBy());
        localizedResource.setDefaultText(localizedResourceDTO.getDefaultText());

        localizedResource.setCreatedAt(LocalDateTime.now());
        localizedResource.setLastModifiedAt(LocalDateTime.now());
        localizedResource.setTranslations(new ArrayList<>());

        return localizedResourceRepository.save(localizedResource);
    }

    @Override
    public LocalizedResource update(String resourceId, LocalizedResourceDTO localizedResourceDTO) throws LocalizedResourceNotFoundException {
        LocalizedResource localizedResource = getById(resourceId);

        localizedResource.setLastModifiedAt(LocalDateTime.now());
        localizedResource.setDefaultText(localizedResourceDTO.getDefaultText());

        return localizedResourceRepository.save(localizedResource);
    }

    @Override
    public LocalizedResource getById(String resourceId) throws LocalizedResourceNotFoundException {
        LocalizedResource localizedResource = localizedResourceRepository.findById(resourceId).orElse(null);
        if(localizedResource == null) throw new LocalizedResourceNotFoundException(resourceId);
        return localizedResource;
    }

    @Override
    public LocalizedResource getById(String resourceId, String alternative) throws LocalizedResourceNotFoundException {

        LocalizedResource localizedResource = localizedResourceRepository.findById(resourceId).orElse(null);
        if(localizedResource != null) return  localizedResource;

        localizedResource = new LocalizedResource();
        localizedResource.setResourceId(resourceId);
        localizedResource.setDefaultText(alternative);
        localizedResource.setLastModifiedAt(LocalDateTime.now());
        localizedResource.setCreatedAt(LocalDateTime.now());
        localizedResource.setCreatedBy("Google translator");

//        localizedResource = localizedResourceRepository.save(localizedResource);

        localizedResource.setTranslations(LocalizedResourceGenerator
                .generateTranslations(localizedResource, alternative, languageService.getStackedAvailableLanguages()));
        localizedResourceRepository.save(localizedResource);
        return localizedResource;
    }

    @Override
    public Object getByIdMap(String resourceId, String alternative) throws LocalizedResourceNotFoundException {
        LocalizedResource localizedResource = getById(resourceId, alternative);
        return getMappedLocalizedObject(localizedResource);
    }

    @Override
    public void delete(String resourceId) throws LocalizedResourceNotFoundException {
        getById(resourceId);
        localizedResourceRepository.deleteById(resourceId);
    }

    @Override
    public List<LocalizedResource> getAll() {
        return localizedResourceRepository.findAll();
    }

    @Override
    public Map<String, Object> getAllAsMap() {
        Map<String, Object> resourceMap = new HashMap<>();
        localizedResourceRepository.findAll().forEach(item -> {
            resourceMap.put(item.getResourceId(), getMappedLocalizedObject(item));
        });
        return resourceMap;
    }

    private Object getMappedLocalizedObject(LocalizedResource resource){
        Map<String, Object> map = new HashMap<>();
        map.put("resourceId", resource.getResourceId());
        map.put("defaultText", resource.getDefaultText());
        map.put("createdBy", resource.getCreatedBy());
        map.put("createdAt", resource.getCreatedAt());
        map.put("lastModifiedAt", resource.getLastModifiedAt());

        Map<String, Object> translations = new HashMap<>();
        for(Translation translation: resource.getTranslations()){
            translations.put(translation.getLanguageName(), translation);
        }

        map.put("translations", translations);
        return map;
    }

    @Override
    public List<LocalizedResource> getByPagination(int itemPerPage, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, itemPerPage);
        Page<LocalizedResource> resourcePage = localizedResourceRepository.findAll(pageable);
        return resourcePage.getContent();
    }

    @Override
    public List<LocalizedResource> searchInResourceId(String keyword) {
        return localizedResourceRepository.findByResourceIdContaining(keyword);
    }

    @Override
    public List<LocalizedResource> getLocalizedResourcesCreatedBy(String createdBy) {
        return localizedResourceRepository.findByCreatedBy(createdBy);
    }
    @Override
    public String getSQLBackup() {
        List<LocalizedResource> localizedResources = getAll();
        String queries = "";

        for (LocalizedResource resource : localizedResources) {
            // Create an insert query for the LocalizedResource
            String resourceQuery = String.format(
                    "INSERT INTO localized_resource (resource_id, default_text, created_by, created_at, last_modified_at) " +
                            "VALUES ('%s', '%s', '%s', '%s', '%s');",
                    resource.getResourceId(),
                    resource.getDefaultText(),
                    resource.getCreatedBy(),
                    resource.getCreatedAt(),
                    resource.getLastModifiedAt()
            );
            queries += resourceQuery;

            // Loop through translations and create insert queries for each
            for (Translation translation : resource.getTranslations()) {
                String translationQuery = String.format(
                        "INSERT INTO translation (translation_id, language_name, language_code, localized_text, resource_id) " +
                                "VALUES (%d, '%s', '%s', '%s', '%s');",
                        translation.getTranslationId(),
                        translation.getLanguageName(),
                        translation.getLanguageCode(),
                        translation.getLocalizedText(),
                        resource.getResourceId()
                );
                queries += resourceQuery + "\n";
            }
            // An additional new line between resource queries for readability
            queries += "\n\n";
        }
        return queries;
    }
}

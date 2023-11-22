package com.ladder.i18n.service.definition;

import com.ladder.i18n.entity.LocalizedResource;
import com.ladder.i18n.exception.DuplicateEntityException;
import com.ladder.i18n.exception.InvalidEntityException;
import com.ladder.i18n.exception.LocalizedResourceNotFoundException;
import com.ladder.i18n.model.LocalizedResourceDTO;

import java.util.List;
import java.util.Map;

public interface LocalizedResourceService {
    LocalizedResource create(LocalizedResourceDTO localizedResourceDTO) throws DuplicateEntityException, InvalidEntityException;
    LocalizedResource update(String resourceId, LocalizedResourceDTO localizedResourceDTO) throws LocalizedResourceNotFoundException;
    LocalizedResource getById(String resourceId) throws LocalizedResourceNotFoundException;
    LocalizedResource getById(String resourceId, String alternative) throws LocalizedResourceNotFoundException;
    void delete(String resourceId) throws LocalizedResourceNotFoundException;
    List<LocalizedResource> getAll();
    Map<String, Object> getAllAsMap();
    List<LocalizedResource> getByPagination(int itemPerPage, int pageNo);
    List<LocalizedResource> searchInResourceId(String keyword);
    List<LocalizedResource> getLocalizedResourcesCreatedBy(String createdBy);
    String getSQLBackup();
    Object getByIdMap(String resourceId, String alternate) throws LocalizedResourceNotFoundException;
}
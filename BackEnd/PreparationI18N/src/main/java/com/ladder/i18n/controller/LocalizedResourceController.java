package com.ladder.i18n.controller;

import com.ladder.i18n.exception.DuplicateEntityException;
import com.ladder.i18n.exception.InvalidEntityException;
import com.ladder.i18n.exception.LocalizedResourceNotFoundException;
import com.ladder.i18n.model.LocalizedResourceDTO;
import com.ladder.i18n.entity.LocalizedResource;
import com.ladder.i18n.service.definition.LocalizedResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/v1/language/resources")
@RequiredArgsConstructor
public class LocalizedResourceController {

    private final LocalizedResourceService localizedResourceService;

    @PostMapping
    public ResponseEntity<String> createLocalizedResource(@RequestBody LocalizedResourceDTO localizedResourceDTO) throws DuplicateEntityException, InvalidEntityException {
        LocalizedResource localizedResource = localizedResourceService.create(localizedResourceDTO);
        return new ResponseEntity<>("Localized resource created.", HttpStatus.CREATED);
    }

    @PutMapping("/{resourceId}")
    public ResponseEntity<String> updateLocalizedResource(@PathVariable String resourceId, @RequestBody LocalizedResourceDTO localizedResourceDTO) throws LocalizedResourceNotFoundException {
        LocalizedResource localizedResource = localizedResourceService.update(resourceId, localizedResourceDTO);
        return new ResponseEntity<>("Localized resource updated.", HttpStatus.OK);
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<LocalizedResource> getLocalizedResource(@PathVariable String resourceId) throws LocalizedResourceNotFoundException {
        LocalizedResource localizedResource = localizedResourceService.getById(resourceId);
        return new ResponseEntity<>(localizedResource, HttpStatus.OK);
    }

    @GetMapping("/{resourceId}/alternate/{alternate}")
    public ResponseEntity<LocalizedResource> getLocalizedResource(@PathVariable String resourceId, @PathVariable String alternate) throws LocalizedResourceNotFoundException {
        LocalizedResource localizedResource = localizedResourceService.getById(resourceId, alternate);
        return new ResponseEntity<>(localizedResource, HttpStatus.OK);
    }

    @GetMapping("/map/{resourceId}/alternate/{alternate}")
    public ResponseEntity<Object> getLocalizedResourceMap(@PathVariable String resourceId, @PathVariable String alternate) throws LocalizedResourceNotFoundException {
        return new ResponseEntity<>(localizedResourceService.getByIdMap(resourceId, alternate), HttpStatus.OK);
    }

    @DeleteMapping("/{resourceId}")
    public ResponseEntity<Void> deleteLocalizedResource(@PathVariable String resourceId) throws LocalizedResourceNotFoundException {
        localizedResourceService.delete(resourceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<LocalizedResource>> getAllLocalizedResources() {
        List<LocalizedResource> localizedResources = localizedResourceService.getAll();
        return new ResponseEntity<>(localizedResources, HttpStatus.OK);
    }

    @GetMapping("/map")
    public ResponseEntity<Map<String, Object>> getAllLocalizedResourcesAsMap() {
        return new ResponseEntity<>(localizedResourceService.getAllAsMap(), HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<LocalizedResource>> searchLocalizedResources(@PathVariable String keyword) {
        List<LocalizedResource> localizedResources = localizedResourceService.searchInResourceId(keyword);
        return new ResponseEntity<>(localizedResources, HttpStatus.OK);
    }

    @GetMapping("/created-by/{createdBy}")
    public ResponseEntity<List<LocalizedResource>> getLocalizedResourcesCreatedBy(@PathVariable String createdBy) {
        List<LocalizedResource> localizedResources = localizedResourceService.getLocalizedResourcesCreatedBy(createdBy);
        return new ResponseEntity<>(localizedResources, HttpStatus.OK);
    }

    @GetMapping("/{itemPerPage}/{pageNo}")
    public ResponseEntity<List<LocalizedResource>> getLocalizedResourcesByPagination(@PathVariable int itemPerPage, @PathVariable int pageNo) {
        List<LocalizedResource> localizedResources = localizedResourceService.getByPagination(itemPerPage, pageNo);
        return new ResponseEntity<>(localizedResources, HttpStatus.OK);
    }

    @GetMapping("/sql-backup")
    public ResponseEntity<String> getSQLBackup() {
        String sqlBackup = localizedResourceService.getSQLBackup();
        return new ResponseEntity<>(sqlBackup, HttpStatus.OK);
    }
}

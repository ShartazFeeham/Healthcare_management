package com.ladder.i18n.controller;

import com.ladder.i18n.exception.DuplicateEntityException;
import com.ladder.i18n.exception.LocalizedResourceNotFoundException;
import com.ladder.i18n.exception.TranslationNotFoundException;
import com.ladder.i18n.model.TranslationDTO;
import com.ladder.i18n.entity.Translation;
import com.ladder.i18n.service.definition.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/language/translations")
@RequiredArgsConstructor
public class TranslationController {

    private final TranslationService translationService;

    @PostMapping
    public ResponseEntity<String> createTranslation(@RequestBody TranslationDTO translationDTO) throws LocalizedResourceNotFoundException, DuplicateEntityException {
        Translation translation = translationService.create(translationDTO);
        return new ResponseEntity<>("Translation created successfully.", HttpStatus.CREATED);
    }

    @PutMapping("/{translationId}")
    public ResponseEntity<String> updateTranslation(@PathVariable Long translationId, @RequestBody TranslationDTO translationDTO) throws TranslationNotFoundException {
        Translation translation = translationService.update(translationId, translationDTO);
        return new ResponseEntity<>("Translation updated successfully.", HttpStatus.OK);
    }

    @GetMapping("/{translationId}")
    public ResponseEntity<Translation> getTranslation(@PathVariable Long translationId) throws TranslationNotFoundException {
        Translation translation = translationService.getById(translationId);
        return new ResponseEntity<>(translation, HttpStatus.OK);
    }

    @DeleteMapping("/{translationId}")
    public ResponseEntity<Void> deleteTranslation(@PathVariable Long translationId) throws TranslationNotFoundException {
        translationService.delete(translationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Translation>> getAllTranslations() {
        List<Translation> translations = translationService.getAllTranslations();
        return new ResponseEntity<>(translations, HttpStatus.OK);
    }

    @GetMapping("/by-localized-resource/{resourceId}")
    public ResponseEntity<List<Translation>> getTranslationsByLocalizedResource(@PathVariable String resourceId) {
        List<Translation> translations = translationService.getByLocalizedResource(resourceId);
        return new ResponseEntity<>(translations, HttpStatus.OK);
    }

    @GetMapping("/by-language-code/{languageCode}")
    public ResponseEntity<List<Translation>> getTranslationsByLanguageCode(@PathVariable String languageCode) {
        List<Translation> translations = translationService.getTranslationsForLanguage(languageCode);
        return new ResponseEntity<>(translations, HttpStatus.OK);
    }

    @GetMapping("/by-language-name/{languageName}")
    public ResponseEntity<List<Translation>> getTranslationsByLanguageName(@PathVariable String languageName) {
        List<Translation> translations = translationService.getTranslationsByLanguageName(languageName);
        return new ResponseEntity<>(translations, HttpStatus.OK);
    }

    @GetMapping("/{itemPerPage}/{pageNo}")
    public ResponseEntity<List<Translation>> getTranslationsByPagination( @PathVariable int itemPerPage, @PathVariable int pageNo) {
        List<Translation> translations = translationService.getByPagination(itemPerPage, pageNo);
        return new ResponseEntity<>(translations, HttpStatus.OK);
    }
}

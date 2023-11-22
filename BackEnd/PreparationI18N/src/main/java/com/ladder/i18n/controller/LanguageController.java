package com.ladder.i18n.controller;

import com.ladder.i18n.entity.Language;
import com.ladder.i18n.exception.DuplicateEntityException;
import com.ladder.i18n.exception.LanguageNotFoundException;
import com.ladder.i18n.model.LanguageDTO;
import com.ladder.i18n.service.definition.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/languages")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    @PostMapping
    public void createLanguage(@RequestBody LanguageDTO languageDTO) throws DuplicateEntityException {
        languageService.create(languageDTO);
    }

    @GetMapping
    public List<Language> getAllLanguages() {
        return languageService.readAll();
    }

    @GetMapping("/{languageId}")
    public Language getLanguageById(@PathVariable Long languageId) throws LanguageNotFoundException {
        return languageService.readById(languageId);
    }

    @PutMapping("/{languageId}")
    public void updateLanguage(@PathVariable Long languageId, @RequestBody LanguageDTO languageDTO)
            throws DuplicateEntityException, LanguageNotFoundException {
        languageService.update(languageId, languageDTO);
    }

    @DeleteMapping("/{languageId}")
    public void deleteLanguage(@PathVariable Long languageId) throws LanguageNotFoundException {
        languageService.delete(languageId);
    }
}

package com.healthcare.integration.controller;

import com.ladder.IntegrationService.exception.GoogleTranslatorException;
import com.ladder.IntegrationService.interfaces.TranslationService;
import com.ladder.IntegrationService.model.LanguageTag;
import com.ladder.IntegrationService.model.TranslationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/translation")
@RequiredArgsConstructor
public class TranslationController {

    private final TranslationService translationService;

    @PostMapping("/translate")
    public ResponseEntity<String> translate(@RequestBody TranslationRequest translationRequest) throws GoogleTranslatorException {
        return ResponseEntity.ok(translationService.translate(translationRequest));
    }

    @GetMapping("/available-languages")
    public ResponseEntity<List<LanguageTag>> getAvailableLanguageTags() {
        return ResponseEntity.of(Optional.ofNullable(translationService.getAvailableLanguageTags()));
    }
}

package com.healthcare.medicines.controller;

import com.healthcare.medicines.services.builder.SearchQueryBuilder;
import com.healthcare.medicines.services.singleton.UnifiedSearch;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {
    private final UnifiedSearch unifiedSearch = UnifiedSearch.getInstance();
    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<SearchQueryBuilder>> search(@PathVariable String keywords){
        return ResponseEntity.ok(unifiedSearch.search(keywords));
    }
}

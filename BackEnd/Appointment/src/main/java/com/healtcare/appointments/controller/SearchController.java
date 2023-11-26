package com.healtcare.appointments.controller;

import com.healtcare.appointments.services.interfaces.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController @AllArgsConstructor
public class SearchController {
    private final SearchService searchService;
    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<Map<String, Object>>> search(@PathVariable String keywords){
        return ResponseEntity.ok(searchService.search(keywords));
    }
}

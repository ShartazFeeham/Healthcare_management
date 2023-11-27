package com.healthcare.doctordata.controller;
import com.healthcare.doctordata.models.FilteredBySpecDTO;
import com.healthcare.doctordata.services.interfaces.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/specializations/{specialization}")
    public ResponseEntity<List<FilteredBySpecDTO>> searchBySpecialization(@PathVariable String specialization) {
        List<FilteredBySpecDTO> doctors = searchService.searchBySpecialization(specialization);
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<Map<String, Object>>> search(@PathVariable String keywords){
        return ResponseEntity.ok(searchService.search(keywords));
    }
}

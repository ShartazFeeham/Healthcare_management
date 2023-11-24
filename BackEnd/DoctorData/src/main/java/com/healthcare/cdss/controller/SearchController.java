package com.healthcare.cdss.controller;
import com.healthcare.cdss.entity.Doctor;
import com.healthcare.cdss.service.interfaces.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/specializations/{specialization}")
    public ResponseEntity<List<Doctor>> searchBySpecialization(@PathVariable String specialization) {
        List<Doctor> doctors = searchService.searchBySpecialization(specialization);
        return ResponseEntity.ok(doctors);
    }
}

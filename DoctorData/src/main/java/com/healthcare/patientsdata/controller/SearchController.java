package com.healthcare.patientsdata.controller;
import com.healthcare.patientsdata.entity.Doctor;
import com.healthcare.patientsdata.service.interfaces.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<List<Doctor>> searchBySpecialization(@PathVariable String specialization) {
        List<Doctor> doctors = searchService.searchBySpecialization(specialization);
        return ResponseEntity.ok(doctors);
    }
}
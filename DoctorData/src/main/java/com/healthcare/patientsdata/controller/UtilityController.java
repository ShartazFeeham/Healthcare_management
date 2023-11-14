package com.healthcare.patientsdata.controller;

import com.healthcare.patientsdata.service.interfaces.UtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/utility")
@RequiredArgsConstructor
public class UtilityController {

    private final UtilityService utilityService;

    @GetMapping("/specializations")
    public ResponseEntity<List<String>> getSpecializations() {
        List<String> specializations = utilityService.getSpecializations();
        return ResponseEntity.ok(specializations);
    }
}

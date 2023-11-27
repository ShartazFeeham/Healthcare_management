package com.healthcare.doctordata.controller;

import com.healthcare.doctordata.services.interfaces.UtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UtilityController {

    private final UtilityService utilityService;

    @GetMapping("/specializations")
    public ResponseEntity<List<String>> getSpecializations() {
        List<String> specializations = utilityService.getSpecializations();
        return ResponseEntity.ok(specializations);
    }
}

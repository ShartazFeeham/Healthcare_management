package com.healthcare.notification.controllers;

import com.healthcare.notification.entities.Preference;
import com.healthcare.notification.exceptions.ItemNotFoundException;
import com.healthcare.notification.service.interfaces.PreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/preferences")
@RequiredArgsConstructor
public class PreferenceController {

    private final PreferenceService preferenceService;

    @GetMapping
    public ResponseEntity<Preference> getPreferenceByUserId() {
        Preference preference = preferenceService.getByUserId("PAT1");
        return ResponseEntity.ok(preference);
    }

    @PostMapping
    public ResponseEntity<Preference> createPreference(@RequestParam String userId, @RequestParam String email) {
        Preference preference = preferenceService.create(userId, email);
        return ResponseEntity.ok(preference);
    }

    @PutMapping
    public ResponseEntity<String> updatePreference(@RequestBody Preference updatedPreference) {
        preferenceService.update("PAT1", updatedPreference);
        return new ResponseEntity<String>("Preference updated", HttpStatus.OK);
    }

    @PostMapping("/device")
    public ResponseEntity<String> addDevice(@RequestParam String deviceCode) {
        preferenceService.addDevice("PAT1", deviceCode);
        return ResponseEntity.ok("Device added.");
    }

    @DeleteMapping("/device")
    public ResponseEntity<String> removeDevice(@RequestParam String deviceCode) throws ItemNotFoundException {
        preferenceService.removeDevice("PAT1", deviceCode);
        return ResponseEntity.ok("Device removed.");
    }
}

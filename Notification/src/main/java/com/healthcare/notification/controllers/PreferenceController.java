package com.healthcare.notification.controllers;

import com.healthcare.notification.entities.Preference;
import com.healthcare.notification.exceptions.ItemNotFoundException;
import com.healthcare.notification.model.DeviceRequest;
import com.healthcare.notification.model.PhoneNoRequest;
import com.healthcare.notification.service.interfaces.PreferenceService;
import com.healthcare.notification.utilities.token.IDExtractor;
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
        Preference preference = preferenceService.getByUserId(IDExtractor.getUserID());
        return ResponseEntity.ok(preference);
    }

    @PostMapping
    public ResponseEntity<Preference> createPreference(@RequestParam String userId, @RequestParam String email) {
        Preference preference = preferenceService.create(userId, email);
        return ResponseEntity.ok(preference);
    }

    @PutMapping
    public ResponseEntity<String> updatePreference(@RequestBody Preference updatedPreference) {
        preferenceService.update(IDExtractor.getUserID(), updatedPreference);
        return new ResponseEntity<String>("Preference updated", HttpStatus.OK);
    }

    @PostMapping("/device")
    public ResponseEntity<String> addDevice(@RequestBody DeviceRequest deviceRequest) {
        preferenceService.addDevice(deviceRequest);
        return ResponseEntity.ok("Device added.");
    }

    @DeleteMapping("/device")
    public ResponseEntity<String> removeDevice(@RequestBody DeviceRequest deviceRequest) throws ItemNotFoundException {
        preferenceService.removeDevice(deviceRequest);
        return ResponseEntity.ok("Device removed.");
    }

    @PostMapping("/phone")
    public ResponseEntity<String> addPhone(@RequestBody PhoneNoRequest phoneNoRequest){
        preferenceService.updatePhoneNo(phoneNoRequest.getUserId(), phoneNoRequest.getPhoneNo());
        return ResponseEntity.ok("Phone number updated.");
    }
}

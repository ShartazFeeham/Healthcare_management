package com.healtcare.appointments.controller;

import com.healtcare.appointments.services.interfaces.TeleSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequiredArgsConstructor
public class TeleSecurityController {
    private final TeleSecurityService teleSecurityService;
    @GetMapping("/tele/verify/{appointmentId}")
    public ResponseEntity<String> verify(@PathVariable String appointmentId){
        teleSecurityService.verify(appointmentId);
        return ResponseEntity.ok("User is verified for this meeting.");
    }
}

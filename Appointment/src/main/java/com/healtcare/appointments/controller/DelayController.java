package com.healtcare.appointments.controller;

import com.healtcare.appointments.services.interfaces.DelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/delays")
public class DelayController {

    private final DelayService delayService;

    @Autowired
    public DelayController(DelayService delayService) {
        this.delayService = delayService;
    }

    @PostMapping("/update/{minutes}")
    public ResponseEntity<?> updateDelay(@PathVariable Integer minutes) {
        delayService.updateDelay(minutes);
        return ResponseEntity.ok("Delay updated successfully");
    }

    @GetMapping("/get/{doctorId}/{shift}/{appointmentTime}")
    public ResponseEntity<Integer> getDelayInMinutes(@PathVariable String doctorId, @PathVariable String shift, @PathVariable LocalDateTime appointmentTime) {
        return ResponseEntity.ok(delayService.getDelayInMinutes(doctorId, shift, appointmentTime));
    }
}

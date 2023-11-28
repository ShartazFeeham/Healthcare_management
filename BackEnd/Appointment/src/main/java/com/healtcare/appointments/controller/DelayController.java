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

    // Endpoint to update the delay for appointments by a specified number of minutes.
    @PostMapping("/update/{minutes}")
    public ResponseEntity<?> updateDelay(@PathVariable Integer minutes) {
        delayService.updateDelay(minutes);
        return ResponseEntity.ok("Delay updated successfully");
    }

    // Endpoint to get the delay in minutes for a specific doctor, shift, and appointment time.
    @GetMapping("/get/{doctorId}/{shift}/{appointmentTime}")
    public ResponseEntity<Integer> getDelayInMinutes(@PathVariable String doctorId, @PathVariable String shift, @PathVariable LocalDateTime appointmentTime) {
        return ResponseEntity.ok(delayService.getDelayInMinutes(doctorId, shift, appointmentTime));
    }

    // Endpoint to get the current delay in minutes for a specific doctor.
    @GetMapping("/get/{doctorId}")
    public ResponseEntity<Integer> getDelayByDoctor(@PathVariable String doctorId){
        return ResponseEntity.ok(delayService.getCurrentDelayInMinutes(doctorId));
    }
}

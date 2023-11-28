package com.healtcare.appointments.controller;

import com.healtcare.appointments.models.ScheduleGetDTO;
import com.healtcare.appointments.models.ScheduleSetDTO;
import com.healtcare.appointments.services.interfaces.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // Endpoint to set the schedule for a doctor.
    @PostMapping("/set")
    public ResponseEntity<String> setSchedule(@RequestBody ScheduleSetDTO scheduleDTO) throws AccessDeniedException {
        scheduleService.setSchedule(scheduleDTO);
        return ResponseEntity.ok("Schedule set successfully!");
    }

    // Endpoint to get the schedule for a specific date and doctor.
    @GetMapping("/get/{date}/{doctorId}")
    public ResponseEntity<ScheduleGetDTO> getSchedule(@PathVariable String date, @PathVariable String doctorId) {
        ScheduleGetDTO schedule = scheduleService.getScheduleByDateAndDoctorId(date, doctorId);
        return ResponseEntity.ok(schedule);
    }

    // Endpoint to get the dates for which a doctor has a schedule.
    @GetMapping("/dates/{doctorId}")
    public ResponseEntity<List<String>> getDates(@PathVariable String doctorId) {
        List<String> dates = scheduleService.getDatesByDoctorId(doctorId).stream()
                .map(Object::toString)
                .toList();
        return ResponseEntity.ok(dates);
    }
}

package com.healtcare.appointments.controller;

import com.healtcare.appointments.models.AppointmentListDTO;
import com.healtcare.appointments.models.AppointmentRequestDTO;
import com.healtcare.appointments.services.interfaces.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/create")
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentRequestDTO appointmentRequestDTO) {
        return ResponseEntity.ok(appointmentService.createAppointment(appointmentRequestDTO));
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<?> getAppointment(@PathVariable String appointmentId) {
        return ResponseEntity.ok(appointmentService.getAppointment(appointmentId));
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<String> cancelAppointment(@PathVariable String appointmentId) throws AccessDeniedException {
        appointmentService.cancelAppointment(appointmentId);
        return ResponseEntity.ok("Appointment canceled successfully");
    }

    @GetMapping("/complete/{patientId}")
    public ResponseEntity<List<AppointmentListDTO>> getCompleteAppointmentsByPatient(@PathVariable String patientId) {
        return ResponseEntity.ok(appointmentService.getCompleteAppointmentsByPatient(patientId));
    }

    @GetMapping("/upcoming/{patientId}")
    public ResponseEntity<List<AppointmentListDTO>> getUpcomingAppointmentsByPatient(@PathVariable String patientId) {
        return ResponseEntity.ok(appointmentService.getUpcomingAppointmentsByPatient(patientId));
    }

    @GetMapping("/count/{doctorId}/{date}/{shift}")
    public ResponseEntity<Integer> countBookedAppointments(@PathVariable String doctorId, @PathVariable String date, @PathVariable String shift) {
        LocalDate parsedDate = LocalDate.parse(date);
        return ResponseEntity.ok(appointmentService.countBookedAppointments(doctorId, parsedDate, shift));
    }

    @GetMapping("/capacity/{doctorId}/{date}/{shift}")
    public ResponseEntity<Integer> countAppointmentCapacity(@PathVariable String doctorId, @PathVariable String date, @PathVariable String shift) {
        LocalDate parsedDate = LocalDate.parse(date);
        return ResponseEntity.ok(appointmentService.countAppointmentCapacity(doctorId, parsedDate, shift));
    }

    @GetMapping("/total/{doctorId}")
    public ResponseEntity<Integer> countTotalAppointmentByDoctorId(@PathVariable String doctorId) {
        return ResponseEntity.ok(appointmentService.countTotalAppointmentByDoctorId(doctorId));
    }

    @GetMapping("/check/{appointmentId}")
    public ResponseEntity<Boolean> checkAppointmentValidity(@PathVariable String appointmentId) {
        return ResponseEntity.ok(appointmentService.checkAppointmentValidity(appointmentId));
    }
}

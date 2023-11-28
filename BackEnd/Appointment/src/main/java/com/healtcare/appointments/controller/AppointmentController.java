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
import java.util.Map;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    // Endpoint to create a new appointment.
    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentRequestDTO appointmentRequestDTO) {
        return ResponseEntity.ok(appointmentService.createAppointment(appointmentRequestDTO));
    }

    // Endpoint to retrieve details of a specific appointment by its ID.
    @GetMapping("/{appointmentId}")
    public ResponseEntity<?> getAppointment(@PathVariable String appointmentId) {
        return ResponseEntity.ok(appointmentService.getAppointment(appointmentId));
    }

    // Endpoint to cancel a specific appointment by its ID.
    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<String> cancelAppointment(@PathVariable String appointmentId) throws AccessDeniedException {
        appointmentService.cancelAppointment(appointmentId);
        return ResponseEntity.ok("Appointment canceled successfully");
    }

    // Endpoint to retrieve a list of completed appointments for a specific patient.
    @GetMapping("/complete/patient/{patientId}")
    public ResponseEntity<List<AppointmentListDTO>> getCompleteAppointmentsByPatient(@PathVariable String patientId) {
        return ResponseEntity.ok(appointmentService.getCompleteAppointmentsByPatient(patientId));
    }

    // Endpoint to retrieve a list of upcoming appointments for a specific patient.
    @GetMapping("/upcoming/patient/{patientId}")
    public ResponseEntity<List<AppointmentListDTO>> getUpcomingAppointmentsByPatient(@PathVariable String patientId) {
        return ResponseEntity.ok(appointmentService.getUpcomingAppointmentsByPatient(patientId));
    }

    // Endpoint to retrieve a list of completed appointments for a specific doctor.
    @GetMapping("/complete/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentListDTO>> getCompleteAppointmentsByDoctor(@PathVariable String doctorId) {
        return ResponseEntity.ok(appointmentService.getCompleteAppointmentsByDoctor(doctorId));
    }

    // Endpoint to retrieve a list of upcoming appointments for a specific doctor.
    @GetMapping("/upcoming/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentListDTO>> getUpcomingAppointmentsByDoctor(@PathVariable String doctorId) {
        return ResponseEntity.ok(appointmentService.getUpcomingAppointmentsByDoctor(doctorId));
    }

    // Endpoint to retrieve a list of upcoming appointments of public users.
    @GetMapping("/complete/public")
    public ResponseEntity<List<AppointmentListDTO>> getPublicUpcoming() {
        return ResponseEntity.ok(appointmentService.getPublicCompletedAppointments());
    }

    // Endpoint to retrieve a list of upcoming appointments of public users.
    @GetMapping("/upcoming/public")
    public ResponseEntity<List<AppointmentListDTO>> getPublicCompleted() {
        return ResponseEntity.ok(appointmentService.getPublicUpcomingAppointments());
    }

    // Endpoint to count the number of booked appointments for a specific doctor on a given date and shift.
    @GetMapping("/booked/{doctorId}/{date}/{shift}")
    public ResponseEntity<Integer> countBookedAppointments(@PathVariable String doctorId, @PathVariable String date, @PathVariable String shift) {
        LocalDate parsedDate = LocalDate.parse(date);
        return ResponseEntity.ok(appointmentService.countBookedAppointments(doctorId, parsedDate, shift));
    }

    // Endpoint to count the appointment capacity for a specific doctor on a given date and shift.
    @GetMapping("/capacity/{doctorId}/{date}/{shift}")
    public ResponseEntity<Integer> countAppointmentCapacity(@PathVariable String doctorId, @PathVariable String date, @PathVariable String shift) {
        LocalDate parsedDate = LocalDate.parse(date);
        return ResponseEntity.ok(appointmentService.countAppointmentCapacity(doctorId, parsedDate, shift));
    }

    // Endpoint to count the total number of appointments for a specific doctor.
    @GetMapping("/total/{doctorId}")
    public ResponseEntity<Integer> countTotalAppointmentByDoctorId(@PathVariable String doctorId) {
        return ResponseEntity.ok(appointmentService.countTotalAppointmentByDoctorId(doctorId));
    }

    // Endpoint to check the validity of a specific appointment by its ID.
    @GetMapping("/check/{appointmentId}")
    public ResponseEntity<Boolean> checkAppointmentValidity(@PathVariable String appointmentId) {
        return ResponseEntity.ok(appointmentService.checkAppointmentValidity(appointmentId));
    }
    // Endpoint to get the total appointments count
    @GetMapping("/total-count")
    public ResponseEntity<Long> getTotalCount() {
        return ResponseEntity.ok(appointmentService.totalCount());
    }
    // Endpoint to get appointment statistics
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        return ResponseEntity.ok(appointmentService.getAppointmentStatistics());
    }
}

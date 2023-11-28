package com.healtcare.community.controller;

import com.healtcare.appointments.controller.AppointmentController;
import com.healtcare.appointments.entities.Appointment;
import com.healtcare.appointments.models.AppointmentListDTO;
import com.healtcare.appointments.models.AppointmentRequestDTO;
import com.healtcare.appointments.services.interfaces.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class AppointmentControllerTest {

    @Mock
    private AppointmentService appointmentService;

    @InjectMocks
    private AppointmentController appointmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateAppointment_Success() {
        AppointmentRequestDTO requestDTO = new AppointmentRequestDTO();
        when(appointmentService.createAppointment(requestDTO)).thenReturn(new Appointment());
        ResponseEntity<?> response = appointmentController.createAppointment(requestDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetAppointment_Success() {
        String appointmentId = "ABC123";
        when(appointmentService.getAppointment(appointmentId)).thenReturn(new Appointment());
        ResponseEntity<?> response = appointmentController.getAppointment(appointmentId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetCompleteAppointmentsByPatient_Success() {
        String patientId = "PID1";
        List<AppointmentListDTO> appointments = List.of(new AppointmentListDTO(), new AppointmentListDTO());
        when(appointmentService.getCompleteAppointmentsByPatient(patientId)).thenReturn(appointments);
        ResponseEntity<List<AppointmentListDTO>> response = appointmentController.getCompleteAppointmentsByPatient(patientId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(appointments, response.getBody());
    }

    @Test
    void testGetUpcomingAppointmentsByPatient_Success() {
        String patientId = "PID1";
        List<AppointmentListDTO> appointments = List.of(new AppointmentListDTO(), new AppointmentListDTO());
        when(appointmentService.getUpcomingAppointmentsByPatient(patientId)).thenReturn(appointments);
        ResponseEntity<List<AppointmentListDTO>> response = appointmentController.getUpcomingAppointmentsByPatient(patientId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(appointments, response.getBody());
    }

    @Test
    void testGetCompleteAppointmentsByDoctor_Success() {
        String doctorId = "DID1";
        List<AppointmentListDTO> appointments = List.of(new AppointmentListDTO(), new AppointmentListDTO());
        when(appointmentService.getCompleteAppointmentsByDoctor(doctorId)).thenReturn(appointments);
        ResponseEntity<List<AppointmentListDTO>> response = appointmentController.getCompleteAppointmentsByDoctor(doctorId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(appointments, response.getBody());
    }

    @Test
    void testGetUpcomingAppointmentsByDoctor_Success() {
        String doctorId = "DID1";
        List<AppointmentListDTO> appointments = List.of(new AppointmentListDTO(), new AppointmentListDTO());
        when(appointmentService.getUpcomingAppointmentsByDoctor(doctorId)).thenReturn(appointments);
        ResponseEntity<List<AppointmentListDTO>> response = appointmentController.getUpcomingAppointmentsByDoctor(doctorId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(appointments, response.getBody());
    }

    @Test
    void testGetPublicUpcoming_Success() {
        List<AppointmentListDTO> appointments = List.of(new AppointmentListDTO(), new AppointmentListDTO());
        when(appointmentService.getPublicUpcomingAppointments()).thenReturn(appointments);
        ResponseEntity<List<AppointmentListDTO>> response = appointmentController.getPublicUpcoming();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testCountBookedAppointments_Success() {
        String doctorId = "DID1";
        String date = "2023-12-01";
        String shift = "Morning";
        when(appointmentService.countBookedAppointments(doctorId, LocalDate.parse(date), shift)).thenReturn(5);
        ResponseEntity<Integer> response = appointmentController.countBookedAppointments(doctorId, date, shift);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(5, response.getBody());
    }

    @Test
    void testCountAppointmentCapacity_Success() {
        String doctorId = "DID1";
        String date = "2023-12-01";
        String shift = "Morning";
        when(appointmentService.countAppointmentCapacity(doctorId, LocalDate.parse(date), shift)).thenReturn(10);
        ResponseEntity<Integer> response = appointmentController.countAppointmentCapacity(doctorId, date, shift);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(10, response.getBody());
    }

    @Test
    void testCountTotalAppointmentByDoctorId_Success() {
        String doctorId = "DID1";
        when(appointmentService.countTotalAppointmentByDoctorId(doctorId)).thenReturn(20);
        ResponseEntity<Integer> response = appointmentController.countTotalAppointmentByDoctorId(doctorId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(20, response.getBody());
    }

    @Test
    void testCheckAppointmentValidity_Success() {
        String appointmentId = "ABC123";
        when(appointmentService.checkAppointmentValidity(appointmentId)).thenReturn(true);
        ResponseEntity<Boolean> response = appointmentController.checkAppointmentValidity(appointmentId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());
    }

    @Test
    void testGetTotalCount_Success() {
        when(appointmentService.totalCount()).thenReturn(50L);
        ResponseEntity<Long> response = appointmentController.getTotalCount();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(50L, response.getBody());
    }

    @Test
    void testGetStats_Success() {
        Map<String, Object> stats = Map.of("total", 100, "upcoming", 30, "completed", 70);
        when(appointmentService.getAppointmentStatistics()).thenReturn(stats);
        ResponseEntity<Map<String, Object>> response = appointmentController.getStats();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(stats, response.getBody());
    }
}

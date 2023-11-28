package com.healtcare.community.service;
import com.healtcare.appointments.entities.Appointment;
import com.healtcare.appointments.exception.ItemNotFoundException;
import com.healtcare.appointments.models.AppointmentListDTO;
import com.healtcare.appointments.repositories.AppointmentRepository;
import com.healtcare.appointments.repositories.ScheduleRepository;
import com.healtcare.appointments.services.implementations.AppointmentServiceImpl;
import com.healtcare.appointments.services.interfaces.DelayService;
import com.healtcare.appointments.utilities.TimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class AppointmentServiceImplTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private TimeFormatter timeFormatter;

    @Mock
    private DelayService delayService;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAppointment_Success() {
        when(appointmentRepository.findById(anyString())).thenReturn(java.util.Optional.of(new Appointment()));
        Appointment appointment = appointmentService.getAppointment("A123");
        assertNotNull(appointment);
    }

    @Test
    void testGetAppointment_ItemNotFoundException() {
        when(appointmentRepository.findById(anyString())).thenReturn(java.util.Optional.empty());
        assertThrows(ItemNotFoundException.class, () -> appointmentService.getAppointment("A123"));
    }


    @Test
    void testTotalCount() {
        when(appointmentRepository.count()).thenReturn(10L);
        assertEquals(10L, appointmentService.totalCount());
    }

    @Test
    void testGetUpcomingAppointmentsByPatient() {
        Appointment appointment = new Appointment();
        appointment.setPatientId("P123");
        appointment.setCancelled(false);
        appointment.setAppointmentTime(LocalDateTime.now().plusHours(2));
        when(appointmentRepository.findByPatientIdAndCancelledAndAppointmentTimeAfter(eq("P123"), eq(false), any(LocalDateTime.class)))
                .thenReturn(Collections.singletonList(appointment));
        List<AppointmentListDTO> appointments = appointmentService.getUpcomingAppointmentsByPatient("P123");
        assertEquals(1, appointments.size());
    }

    @Test
    void testGetUpcomingAppointmentsByDoctor() {
        Appointment appointment = new Appointment();
        appointment.setDoctorId("D456");
        appointment.setCancelled(false);
        appointment.setAppointmentTime(LocalDateTime.now().plusHours(2));
        when(appointmentRepository.findByDoctorIdAndCancelledAndAppointmentTimeAfter(eq("D456"), eq(false), any(LocalDateTime.class)))
                .thenReturn(Collections.singletonList(appointment));
        List<AppointmentListDTO> appointments = appointmentService.getUpcomingAppointmentsByDoctor("D456");
        assertEquals(1, appointments.size());
    }

    @Test
    void testCountBookedAppointments() {
        when(appointmentRepository.countByDoctorIdAndDateAndShift(eq("D456"), any(LocalDate.class), eq("Shift1"))).thenReturn(5);
        assertEquals(5, appointmentService.countBookedAppointments("D456", LocalDate.now(), "Shift1"));
    }

    @Test
    void testCountAppointmentCapacity() {
        when(appointmentRepository.countByDoctorIdAndDateAndShiftAndCancelled(eq("D456"), any(LocalDate.class), eq("Shift1"), eq(false))).thenReturn(10);
        assertEquals(10, appointmentService.countAppointmentCapacity("D456", LocalDate.now(), "Shift1"));
    }

    @Test
    void testCountTotalAppointmentByDoctorId() {
        when(appointmentRepository.countByDoctorId(eq("D456"))).thenReturn(15);
        assertEquals(15, appointmentService.countTotalAppointmentByDoctorId("D456"));
    }

    @Test
    void testCheckAppointmentValidity() {
        when(appointmentRepository.existsById(eq("A123"))).thenReturn(true);
        assertTrue(appointmentService.checkAppointmentValidity("A123"));
    }
}

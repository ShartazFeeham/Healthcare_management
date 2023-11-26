package com.healtcare.appointments.repositories;

import com.healtcare.appointments.entities.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {
    List<Appointment> findByDoctorIdAndDateAndShift(String doctorId, LocalDate date, String shift);
    List<Appointment> findByPatientIdAndCancelled(String patientId, boolean cancelled);
    List<Appointment> findByDoctorIdAndCancelled(String doctorId, boolean cancelled);
    List<Appointment> findByPatientIdAndCancelledAndAppointmentTimeAfter(String patientId, boolean cancelled, LocalDateTime appointmentTime);
    List<Appointment> findByDoctorIdAndCancelledAndAppointmentTimeAfter(String patientId, boolean cancelled, LocalDateTime appointmentTime);
    int countByDoctorIdAndDateAndShift(String doctorId, LocalDate date, String shift);
    int countByDoctorIdAndDateAndShiftAndCancelled(String doctorId, LocalDate date, String shift, boolean cancelled);
    List<Appointment> findByAppointmentTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
    int countByDoctorId(String doctorId);
    long countByIdStartingWith(String idPattern);
}

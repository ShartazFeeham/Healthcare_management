package com.healtcare.appointments.repositories;

import com.healtcare.appointments.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
    List<Appointment> findByPatientIdStartingWithAndCancelledAndAppointmentTimeAfter(String patientIdPrefix, boolean cancelled, LocalDateTime appointmentTime);
    List<Appointment> findByPatientIdStartingWithAndCancelledAndAppointmentTimeBefore(String patientIdPrefix, boolean cancelled, LocalDateTime appointmentTime);
    @Query("SELECT NEW map(a.shift as name, COUNT(a) as value) FROM Appointment a GROUP BY a.shift")
    List<Map<String, Object>> findShiftStatistics();
    @Query("SELECT NEW map(a.type as name, COUNT(a) as value) FROM Appointment a GROUP BY a.type")
    List<Map<String, Object>> findTypeStatistics();
    @Query("SELECT NEW map(a.date as name, COUNT(a) as value) FROM Appointment a GROUP BY a.date")
    List<Map<String, Object>> findDailyStatistics();
}

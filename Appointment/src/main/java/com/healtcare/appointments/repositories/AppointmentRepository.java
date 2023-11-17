package com.healtcare.appointments.repositories;

import com.healtcare.appointments.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorIdAndDateAndShift(String doctorId, LocalDate date, String shift);
}

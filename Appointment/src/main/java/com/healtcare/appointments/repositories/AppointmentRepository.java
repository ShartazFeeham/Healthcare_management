package com.healtcare.appointments.repositories;

import com.healtcare.appointments.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}

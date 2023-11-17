package com.healtcare.appointments.repositories;

import com.healtcare.appointments.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}

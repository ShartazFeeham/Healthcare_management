package com.healtcare.appointments.repositories;

import com.healtcare.appointments.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Optional<Schedule> findByDateAndDoctorId(LocalDate date, String doctorId);

    @Query("SELECT DISTINCT s.date FROM Schedule s WHERE s.doctorId = :doctorId")
    List<LocalDate> findDistinctDatesByDoctorId(@Param("doctorId") String doctorId);
}

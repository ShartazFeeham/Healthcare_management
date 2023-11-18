package com.healtcare.appointments.repositories;

import com.healtcare.appointments.entities.Delay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DelayRepository extends JpaRepository<Delay, String> {
}

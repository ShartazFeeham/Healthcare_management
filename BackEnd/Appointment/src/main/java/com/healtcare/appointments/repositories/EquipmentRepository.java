package com.healtcare.appointments.repositories;

import com.healtcare.appointments.entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, String> {
}

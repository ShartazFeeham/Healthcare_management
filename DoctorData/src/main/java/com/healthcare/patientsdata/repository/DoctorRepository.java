package com.healthcare.patientsdata.repository;

import com.healthcare.patientsdata.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, String> {
}

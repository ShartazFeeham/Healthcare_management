package com.healthcare.doctordata.repository;

import com.healthcare.doctordata.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, String> {
    List<Doctor> findBySpecializationsContainingIgnoreCase(String specialization);
    long countByUserIdStartingWith(String idPattern);
}

package com.healtcare.appointments.repositories;

import com.healtcare.appointments.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, String> {
    List<Review> findAllByDoctorId(String doctorId);
    Integer countByDoctorId(String doctorId);
}

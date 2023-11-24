package com.healtcare.appointments.services.interfaces;

import com.healtcare.appointments.entities.Review;

import java.util.List;

public interface ReviewService {
    void create(String appointmentId, Integer rating, String comment);
    void update(String appointmentId, Integer rating, String comment);
    void delete(String appointmentId);
    Review getReviewById(String appointmentId);
    List<Review> getAllReviewsByDoctor(String doctorId);
    Integer getReviewCount(String doctorId);
}

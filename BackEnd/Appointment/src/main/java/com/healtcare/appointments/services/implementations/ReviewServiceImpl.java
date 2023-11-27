package com.healtcare.appointments.services.implementations;
import com.healtcare.appointments.entities.Appointment;
import com.healtcare.appointments.entities.Review;
import com.healtcare.appointments.exception.AccessDeniedException;
import com.healtcare.appointments.exception.ItemNotFoundException;
import com.healtcare.appointments.repositories.ReviewRepository;
import com.healtcare.appointments.services.interfaces.AppointmentService;
import com.healtcare.appointments.services.interfaces.ReviewService;
import com.healtcare.appointments.utilities.TimeFormatter;
import com.healtcare.appointments.utilities.token.IDExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final AppointmentService appointmentService;
    private final TimeFormatter timeFormatter;

    private String checkIfReviewableAndGetDocId(String appointmentId){
        Appointment appointment = appointmentService.getAppointment(appointmentId);
        if(!appointment.getPatientId().equals(IDExtractor.getUserID())){
            throw new AccessDeniedException("You can only review on your own appointments, this review doesn't belong to you.");
        }
        return appointment.getDoctorId();
    }

    @Override
    public void create(String appointmentId, Integer rating, String comment) {
        Review review = new Review();
        review.setId(appointmentId);
        review.setDoctorId(checkIfReviewableAndGetDocId(appointmentId));
        review.setRating(rating);
        review.setComment(comment);
        review.setUserId(IDExtractor.getUserID());
        review.setDate(timeFormatter.formatDate(LocalDate.now()));
        reviewRepository.save(review);
    }

    @Override
    public void update(String appointmentId, Integer rating, String comment) {
        Optional<Review> reviewOp = reviewRepository.findById(appointmentId);
        if(reviewOp.isEmpty()) throw new ItemNotFoundException("review", "appointment id " + appointmentId);
        Review review = reviewOp.get();
        checkIfReviewableAndGetDocId(appointmentId);
        review.setRating(rating);
        review.setComment(comment);
        reviewRepository.save(review);
    }

    @Override
    public void delete(String appointmentId) {
        checkIfReviewableAndGetDocId(appointmentId);
        reviewRepository.deleteById(appointmentId);
    }

    @Override
    public Review getReviewById(String appointmentId) {
        return reviewRepository.findById(appointmentId)
                .orElseThrow(() -> new ItemNotFoundException("review", appointmentId));
    }

    @Override
    public List<Review> getAllReviewsByDoctor(String doctorId) {
        return reviewRepository.findAllByDoctorId(doctorId);
    }

    @Override
    public Integer getReviewCount(String doctorId) {
        return reviewRepository.countByDoctorId(doctorId);
    }
}

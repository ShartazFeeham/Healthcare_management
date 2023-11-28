package com.healtcare.appointments.controller;

import com.healtcare.appointments.entities.Review;
import com.healtcare.appointments.services.interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // Endpoint to create a new review for an appointment.
    @PostMapping
    public ResponseEntity<String> createReview(@RequestParam String appointmentId, @RequestParam Integer rating, @RequestParam String comment) {
        reviewService.create(appointmentId, rating, comment);
        return ResponseEntity.status(HttpStatus.CREATED).body("Review created successfully");
    }

    // Endpoint to update an existing review for an appointment by its ID.
    @PutMapping("/{appointmentId}")
    public ResponseEntity<String> updateReview(@PathVariable String appointmentId, @RequestParam Integer rating, @RequestParam String comment) {
        reviewService.update(appointmentId, rating, comment);
        return ResponseEntity.status(HttpStatus.OK).body("Review updated successfully");
    }

    // Endpoint to delete a review for an appointment by its ID.
    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<String> deleteReview(@PathVariable String appointmentId) {
        reviewService.delete(appointmentId);
        return ResponseEntity.status(HttpStatus.OK).body("Review deleted successfully");
    }

    // Endpoint to get details of a review for an appointment by its ID.
    @GetMapping("/{appointmentId}")
    public ResponseEntity<Review> getReviewById(@PathVariable String appointmentId) {
        Review review = reviewService.getReviewById(appointmentId);
        return ResponseEntity.status(HttpStatus.OK).body(review);
    }

    // Endpoint to get all reviews for a doctor.
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Review>> getAllReviewsByDoctor(@PathVariable String doctorId) {
        List<Review> reviews = reviewService.getAllReviewsByDoctor(doctorId);
        return ResponseEntity.status(HttpStatus.OK).body(reviews);
    }

    // Endpoint to get the count of reviews for a doctor.
    @GetMapping("/doctor/count/{doctorId}")
    public ResponseEntity<Integer> getReviewsCount(@PathVariable String doctorId){
        return ResponseEntity.ok(reviewService.getReviewCount(doctorId));
    }
}

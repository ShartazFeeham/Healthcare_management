package com.healthcare.cdss.services;

import com.healthcare.cdss.exceptions.AccessDeniedException;
import com.healthcare.cdss.exceptions.InvalidRequestException;
import com.healthcare.cdss.exceptions.ItemNotFoundException;
import com.healthcare.cdss.entity.Treatment;
import com.healthcare.cdss.repository.TreatmentRepository;
import com.healthcare.cdss.utilities.token.IDExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepository treatmentRepository;

    // Method to create a new treatment
    @Override
    public void create(Treatment treatment) throws InvalidRequestException {
        // Set patient ID if the user is a patient
        if (IDExtractor.getUserID().startsWith("P")) {
            treatment.setPatientId(IDExtractor.getUserID());
        }
        // Validate and save the treatment
        if (treatment.getPatientId() == null) {
            throw new InvalidRequestException("Patient ID cannot be empty!");
        }
        treatment.setAuthorId(IDExtractor.getUserID());
        treatment.generateKeywords();
        treatmentRepository.save(treatment);
    }

    // Method to update an existing treatment
    @Override
    public void update(Long treatmentId, Treatment treatment) throws ItemNotFoundException, InvalidRequestException, AccessDeniedException {
        Optional<Treatment> treatmentOp = treatmentRepository.findById(treatmentId);
        if (treatmentOp.isEmpty()) throw new ItemNotFoundException("treatment", treatmentId.toString());
        Treatment existing = treatmentOp.get();

        // Check if the user has permission to update the treatment
        if (!existing.getAuthorId().equals(IDExtractor.getUserID()))
            throw new AccessDeniedException("You don't have permission to update a treatment created by someone else.");
        treatment.setId(treatmentId);
        treatment.setPatientId(existing.getPatientId());
        treatment.setAuthorId(existing.getAuthorId());
        // Call create method to validate and save the updated treatment
        create(treatment);
    }

    // Method to delete an existing treatment
    @Override
    public void delete(Long treatmentId) throws ItemNotFoundException, AccessDeniedException {
        Optional<Treatment> treatmentOp = treatmentRepository.findById(treatmentId);
        if (treatmentOp.isEmpty()) throw new ItemNotFoundException("treatment", treatmentId.toString());
        Treatment existing = treatmentOp.get();

        // Check if the user has permission to delete the treatment
        if (!existing.getAuthorId().equals(IDExtractor.getUserID()))
            throw new AccessDeniedException("You don't have permission to delete a treatment created by someone else.");
        treatmentRepository.delete(existing);
    }

    // Method to get a treatment by its ID
    @Override
    public Treatment getById(Long treatmentId) throws ItemNotFoundException, AccessDeniedException {
        Optional<Treatment> treatmentOp = treatmentRepository.findById(treatmentId);
        if (treatmentOp.isEmpty()) throw new ItemNotFoundException("treatment", treatmentId.toString());

        // Check if the user has permission to view the treatment
        if (IDExtractor.getUserID().startsWith("D") || IDExtractor.getUserID().startsWith("A")
                || IDExtractor.getUserID().equals(treatmentOp.get().getPatientId())) {
            return treatmentOp.get();
        } else {
            throw new AccessDeniedException("You don't have permission to see other patients' treatment info");
        }
    }

    // Method to get all treatments for a given patient
    @Override
    public List<Treatment> getByPatientId(String patientId) throws AccessDeniedException {
        // Check if the user has permission to view treatments for the specified patient
        if (IDExtractor.getUserID().startsWith("D") || IDExtractor.getUserID().startsWith("A")
                || IDExtractor.getUserID().equals(patientId)) {
            return treatmentRepository.findByPatientId(patientId);
        } else {
            throw new AccessDeniedException("You don't have permission to see other patients' treatment info");
        }
    }

    // Method to get all treatments created by a specific author
    @Override
    public List<Treatment> getByAuthorId(String authorId) throws AccessDeniedException {
        // Check if the user has permission to view treatments created by the specified author
        if (IDExtractor.getUserID().startsWith("D") || IDExtractor.getUserID().startsWith("A")
                || IDExtractor.getUserID().equals(authorId)) {
            return treatmentRepository.findByAuthorId(authorId).stream()
                    .sorted(Comparator.comparing(Treatment::getId).reversed())
                    .collect(Collectors.toList());
        } else {
            throw new AccessDeniedException("You don't have permission to see other patients' treatment info");
        }
    }
}

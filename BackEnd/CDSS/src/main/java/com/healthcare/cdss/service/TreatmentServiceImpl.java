package com.healthcare.cdss.service;

import com.healthcare.cdss.entity.Treatment;
import com.healthcare.cdss.exceptions.AccessDeniedException;
import com.healthcare.cdss.exceptions.InvalidRequestException;
import com.healthcare.cdss.exceptions.ItemNotFoundException;
import com.healthcare.cdss.repository.TreatmentRepository;
import com.healthcare.cdss.utilities.token.IDExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class TreatmentServiceImpl implements TreatmentService{

    private final TreatmentRepository treatmentRepository;

    @Override
    public void create(Treatment treatment) throws InvalidRequestException {
        if(IDExtractor.getUserID().startsWith("P")){
            treatment.setPatientId(IDExtractor.getUserID());
        }
        if(treatment.getPatientId() == null){
            throw new InvalidRequestException("Patient ID can not be empty!");
        }
        treatment.setAuthorId(IDExtractor.getUserID());
        treatment.generateKeywords();
        treatmentRepository.save(treatment);
    }

    @Override
    public void update(Long treatmentId, Treatment treatment) throws ItemNotFoundException, InvalidRequestException, AccessDeniedException {
        Treatment existing = getById(treatmentId);
        if(!existing.getAuthorId().equals(IDExtractor.getUserID()))
            throw new AccessDeniedException("You don't have permission to update a treatment that is created by someone else.");
        treatment.setId(treatmentId);
        treatment.setPatientId(existing.getPatientId());
        treatment.setAuthorId(existing.getAuthorId());
        create(treatment);
    }

    @Override
    public void delete(Long treatmentId) throws ItemNotFoundException, AccessDeniedException {
        Treatment existing = getById(treatmentId);
        if(!existing.getAuthorId().equals(IDExtractor.getUserID()))
            throw new AccessDeniedException("You don't have permission to delete a treatment that is created by someone else.");
        treatmentRepository.delete(existing);
    }

    @Override
    public Treatment getById(Long treatmentId) throws ItemNotFoundException {
        Optional<Treatment> treatmentOp = treatmentRepository.findById(treatmentId);
        if(treatmentOp.isEmpty()) throw new ItemNotFoundException("treatment", treatmentId.toString());
        return treatmentOp.get();
    }

    @Override
    public List<Treatment> getByPatientId(String patientId) {
        return treatmentRepository.findByPatientId(patientId);
    }

    @Override
    public List<Treatment> getByDoctorId(String doctorId) {
        return treatmentRepository.findByAuthorId(doctorId);
    }
}

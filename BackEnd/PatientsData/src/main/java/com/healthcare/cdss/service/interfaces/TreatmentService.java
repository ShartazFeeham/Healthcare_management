package com.healthcare.cdss.service.interfaces;

import com.healthcare.cdss.entity.Treatment;
import com.healthcare.cdss.exceptions.AccessMismatchException;
import com.healthcare.cdss.exceptions.ItemNotFoundException;

import java.util.List;

public interface TreatmentService {
    List<Treatment> getPatientTreatments(String userId) throws ItemNotFoundException;
    Treatment read(Long treatmentId) throws ItemNotFoundException;
    void create(String userId, Treatment treatmentDTO) throws ItemNotFoundException, AccessMismatchException;
    void update(Long treatmentId, Treatment treatmentDTO) throws ItemNotFoundException, AccessMismatchException;
    void delete(Long treatmentId) throws ItemNotFoundException, AccessMismatchException;
}

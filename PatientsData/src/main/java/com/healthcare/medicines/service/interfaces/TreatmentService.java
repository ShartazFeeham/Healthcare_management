package com.healthcare.medicines.service.interfaces;

import com.healthcare.medicines.entity.Treatment;
import com.healthcare.medicines.exceptions.AccessMismatchException;
import com.healthcare.medicines.exceptions.ItemNotFoundException;

import java.util.List;

public interface TreatmentService {
    List<Treatment> getPatientTreatments(String userId) throws ItemNotFoundException;
    Treatment read(Long treatmentId) throws ItemNotFoundException;
    void create(String userId, Treatment treatmentDTO) throws ItemNotFoundException, AccessMismatchException;
    void update(Long treatmentId, Treatment treatmentDTO) throws ItemNotFoundException, AccessMismatchException;
    void delete(Long treatmentId) throws ItemNotFoundException, AccessMismatchException;
}

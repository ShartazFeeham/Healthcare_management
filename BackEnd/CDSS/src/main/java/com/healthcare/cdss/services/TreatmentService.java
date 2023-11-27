package com.healthcare.cdss.services;

import com.healthcare.cdss.exceptions.AccessDeniedException;
import com.healthcare.cdss.exceptions.InvalidRequestException;
import com.healthcare.cdss.exceptions.ItemNotFoundException;
import com.healthcare.cdss.entity.Treatment;

import java.util.List;

public interface TreatmentService {
    void create(Treatment treatment) throws InvalidRequestException;
    void update(Long treatmentId, Treatment treatment) throws ItemNotFoundException, InvalidRequestException, AccessDeniedException;
    void delete(Long treatmentId) throws ItemNotFoundException, AccessDeniedException;
    Treatment getById(Long treatment) throws ItemNotFoundException, AccessDeniedException;
    List<Treatment> getByPatientId(String patientId) throws AccessDeniedException;
    List<Treatment> getByAuthorId(String doctorId) throws AccessDeniedException;
}

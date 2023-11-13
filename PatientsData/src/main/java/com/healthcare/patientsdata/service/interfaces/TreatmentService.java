package com.healthcare.patientsdata.service.interfaces;

import com.healthcare.patientsdata.entity.Treatment;
import com.healthcare.patientsdata.exceptions.ItemNotFoundException;

import java.util.List;

public interface TreatmentService {
    List<Treatment> getPatientTreatments(String userId) throws ItemNotFoundException;
    Treatment read(Long treatmentId) throws ItemNotFoundException;
    void create(String userId, Treatment treatmentDTO) throws ItemNotFoundException;
    void update(Treatment treatmentDTO) throws ItemNotFoundException;
    void delete(Long treatmentId) throws ItemNotFoundException;
}

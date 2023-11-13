package com.healthcare.patientsdata.service.interfaces;

import com.healthcare.patientsdata.entity.Treatment;

import java.util.List;

public interface TreatmentService {
    List<Treatment> getPatientTreatments(String userId);
    Treatment read(Long treatmentId);
    void create(String userId, Treatment treatmentDTO);
    void update(Treatment treatmentDTO);
    void delete(Long treatmentId);
}

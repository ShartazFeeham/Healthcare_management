package com.healthcare.patientsdata.service.implemenatations;
import com.healthcare.patientsdata.entity.Treatment;
import com.healthcare.patientsdata.repository.TreatmentRepository;
import com.healthcare.patientsdata.service.interfaces.TreatmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepository treatmentRepository;

    @Override
    public List<Treatment> getPatientTreatments(String userId) {
        // Validation logic (if needed)
        return treatmentRepository.findAllByPatientUserId(userId);
    }

    @Override
    public Treatment read(Long treatmentId) {
        // Validation logic
        Optional<Treatment> treatmentOptional = treatmentRepository.findById(treatmentId);
        if (treatmentOptional.isEmpty()) {
            // Throw an exception - Treatment not found
        }
        return treatmentOptional.get();
    }

    @Override
    public void create(String userId, Treatment treatmentDTO) {
        // Validation logic
        // Ensure that the patient ID in the treatmentDTO matches the provided userId
        if (!userId.equals(treatmentDTO.getPatient().getUserId())) {
            // Throw an exception - Patient ID mismatch
        }
        treatmentRepository.save(treatmentDTO);
    }

    @Override
    public void update(Treatment treatmentDTO) {
        // Validation logic (if needed)
        treatmentRepository.save(treatmentDTO);
    }

    @Override
    public void delete(Long treatmentId) {
        // Validation logic
        if (!treatmentRepository.existsById(treatmentId)) {
            // Throw an exception - Treatment not found
        }
        treatmentRepository.deleteById(treatmentId);
    }
}
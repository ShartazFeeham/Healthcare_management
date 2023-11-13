package com.healthcare.patientsdata.service.implemenatations;
import com.healthcare.patientsdata.entity.Patient;
import com.healthcare.patientsdata.models.PatientBioDTO;
import com.healthcare.patientsdata.models.PatientHealthDTO;
import com.healthcare.patientsdata.models.PatientProfileUpdateDTO;
import com.healthcare.patientsdata.models.PatientRegisterDTO;
import com.healthcare.patientsdata.repository.PatientRepository;
import com.healthcare.patientsdata.service.interfaces.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public void register(PatientRegisterDTO patientRegisterDTO) {
        // Validation logic (if needed)
        // Example: Check if the email is unique
        if (patientRepository.existsByEmail(patientRegisterDTO.getEmail())) {
            // Throw an exception - Email already exists
        }

        // Convert DTO to entity and save
        Patient patient = new Patient();
        // Set other properties from the DTO
        patientRepository.save(patient);
    }

    @Override
    public PatientBioDTO getPatientBio(String userId) {
        // Validation logic
        Patient patient = patientRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + userId));

        // Convert entity to DTO and return
        PatientBioDTO bioDTO = new PatientBioDTO();
        // Set properties from the patient entity
        return bioDTO;
    }

    @Override
    public PatientHealthDTO getPatientHealth(String userId) {
        // Validation logic
        Patient patient = patientRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + userId));

        // Convert entity to DTO and return
        PatientHealthDTO healthDTO = new PatientHealthDTO();
        // Set properties from the patient entity
        return healthDTO;
    }

    @Override
    public void updatePatientProfile(String userId, PatientProfileUpdateDTO request) {
        // Validation logic
        Patient patient = patientRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + userId));

        // Update patient entity with data from the DTO
        // Example: patient.setFirstName(request.getFirstName());
        patientRepository.save(patient);
    }
}
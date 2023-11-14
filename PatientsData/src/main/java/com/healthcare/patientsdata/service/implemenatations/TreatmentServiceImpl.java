package com.healthcare.patientsdata.service.implemenatations;
import com.healthcare.patientsdata.entity.Patient;
import com.healthcare.patientsdata.entity.Treatment;
import com.healthcare.patientsdata.exceptions.AccessMismatchException;
import com.healthcare.patientsdata.exceptions.ItemNotFoundException;
import com.healthcare.patientsdata.repository.PatientRepository;
import com.healthcare.patientsdata.repository.TreatmentRepository;
import com.healthcare.patientsdata.service.interfaces.TreatmentService;
import com.healthcare.patientsdata.utilities.token.IDExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final PatientRepository patientRepository;

    @Override
    public List<Treatment> getPatientTreatments(String userId) throws ItemNotFoundException {
        Optional<Patient> patient = patientRepository.findById(userId);
        if(patient.isEmpty()) throw new ItemNotFoundException("patient", userId);
        return patient.get().getTreatments();
    }

    @Override
    public Treatment read(Long treatmentId) throws ItemNotFoundException {
        Optional<Treatment> treatmentOptional = treatmentRepository.findById(treatmentId);
        if (treatmentOptional.isEmpty()) throw new ItemNotFoundException("treatment", treatmentId.toString());
        return treatmentOptional.get();
    }

    @Override
    public void create(String userId, Treatment treatmentDTO) throws ItemNotFoundException, AccessMismatchException {
        if(!(userId.startsWith("A") || userId.startsWith("D") || userId.equals(IDExtractor.getUserID())))
            throw new AccessMismatchException(
                "Requested user has no permission to modify other users' treatment data! " +
                        "Further attempt can result into ban.");

        Optional<Patient> patient = patientRepository.findById(userId);
        if(patient.isEmpty()) throw new ItemNotFoundException("patient", userId);

        treatmentDTO.setPatient(patient.get());
        treatmentDTO.setReportWriter(IDExtractor.getUserID());
        treatmentRepository.save(treatmentDTO);
    }

    @Override
    public void update(Long treatmentId, Treatment treatmentDTO) throws ItemNotFoundException, AccessMismatchException {
        Treatment treatment = read(treatmentId);
        if(!treatment.getReportWriter().equals(IDExtractor.getUserID()))
            throw new AccessMismatchException(
                    "Only the person who wrote the treatment can update the treatment.");

        treatmentDTO.setId(treatmentId);
        treatmentDTO.setPatient(treatment.getPatient());
        treatmentRepository.save(treatmentDTO);
    }

    @Override
    public void delete(Long treatmentId) throws ItemNotFoundException, AccessMismatchException {
        Treatment treatment = read(treatmentId);
        if(!treatment.getReportWriter().equals(IDExtractor.getUserID()))
            throw new AccessMismatchException(
                    "Only the person who wrote the treatment can update the treatment.");
        treatmentRepository.deleteById(treatmentId);
    }
}
package com.healthcare.medicines.service.interfaces;

import com.healthcare.medicines.exceptions.InternalCommunicationException;
import com.healthcare.medicines.exceptions.ItemNotFoundException;
import com.healthcare.medicines.models.PatientBioDTO;
import com.healthcare.medicines.models.PatientHealthDTO;
import com.healthcare.medicines.models.PatientProfileUpdateDTO;
import com.healthcare.medicines.models.PatientRegisterDTO;

public interface PatientService {
    public void register(PatientRegisterDTO patientRegisterDTO) throws InternalCommunicationException;
    PatientBioDTO getPatientBio(String userId) throws ItemNotFoundException;
    PatientHealthDTO getPatientHealth(String userId) throws ItemNotFoundException;
    void updatePatientProfile(String userId, PatientProfileUpdateDTO request) throws ItemNotFoundException;
}

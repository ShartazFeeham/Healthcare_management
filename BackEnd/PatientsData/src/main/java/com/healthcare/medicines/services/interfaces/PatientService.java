package com.healthcare.medicines.services.interfaces;

import com.healthcare.medicines.exceptions.CustomException;
import com.healthcare.medicines.exceptions.InternalCommunicationException;
import com.healthcare.medicines.exceptions.ItemNotFoundException;
import com.healthcare.medicines.models.*;

public interface PatientService {
    public void register(PatientRegisterDTO patientRegisterDTO) throws InternalCommunicationException;
    PatientBioDTO getPatientBio(String userId) throws ItemNotFoundException;
    PatientHealthDTO getPatientHealth(String userId) throws CustomException;
    void updatePatientProfile(String userId, PatientProfileUpdateDTO request) throws ItemNotFoundException;
    UserMinimalInfoDTO getUserMinimalInfo(String userId) throws ItemNotFoundException;
}

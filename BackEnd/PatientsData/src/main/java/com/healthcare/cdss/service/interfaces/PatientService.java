package com.healthcare.cdss.service.interfaces;

import com.healthcare.cdss.exceptions.InternalCommunicationException;
import com.healthcare.cdss.exceptions.ItemNotFoundException;
import com.healthcare.cdss.models.*;

public interface PatientService {
    public void register(PatientRegisterDTO patientRegisterDTO) throws InternalCommunicationException;
    PatientBioDTO getPatientBio(String userId) throws ItemNotFoundException;
    PatientHealthDTO getPatientHealth(String userId) throws ItemNotFoundException;
    void updatePatientProfile(String userId, PatientProfileUpdateDTO request) throws ItemNotFoundException;
    UserMinimalInfoDTO getUserMinimalInfo(String userId) throws ItemNotFoundException;
}

package com.healthcare.patientsdata.service.interfaces;

import com.healthcare.patientsdata.exceptions.InternalCommunicationException;
import com.healthcare.patientsdata.exceptions.ItemNotFoundException;
import com.healthcare.patientsdata.models.PatientBioDTO;
import com.healthcare.patientsdata.models.PatientHealthDTO;
import com.healthcare.patientsdata.models.PatientProfileUpdateDTO;
import com.healthcare.patientsdata.models.PatientRegisterDTO;

public interface PatientService {
    public void register(PatientRegisterDTO patientRegisterDTO) throws InternalCommunicationException;
    PatientBioDTO getPatientBio(String userId) throws ItemNotFoundException;
    PatientHealthDTO getPatientHealth(String userId) throws ItemNotFoundException;
    void updatePatientProfile(String userId, PatientProfileUpdateDTO request) throws ItemNotFoundException;
}

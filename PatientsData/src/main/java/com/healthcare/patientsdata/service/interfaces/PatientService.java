package com.healthcare.patientsdata.service.interfaces;

import com.healthcare.patientsdata.models.PatientBioDTO;
import com.healthcare.patientsdata.models.PatientHealthDTO;
import com.healthcare.patientsdata.models.PatientProfileUpdateDTO;
import com.healthcare.patientsdata.models.PatientRegisterDTO;

public interface PatientService {
    public void register(PatientRegisterDTO patientRegisterDTO);
    PatientBioDTO getPatientBio(String userId);
    PatientHealthDTO getPatientHealth(String userId);
    void updatePatientProfile(String userId, PatientProfileUpdateDTO request);
}

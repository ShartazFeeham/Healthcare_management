package com.healthcare.patients.services.interfaces;

import com.healthcare.patients.entity.Patient;
import com.healthcare.patients.exceptions.CustomException;
import com.healthcare.patients.exceptions.InternalCommunicationException;
import com.healthcare.patients.exceptions.ItemNotFoundException;
import com.healthcare.patients.models.*;
import com.healthcare.patients.models.*;

import java.util.List;

public interface PatientService {
    public void register(PatientRegisterDTO patientRegisterDTO) throws InternalCommunicationException;
    PatientBioDTO getPatientBio(String userId) throws ItemNotFoundException;
    PatientHealthDTO getPatientHealth(String userId) throws CustomException;
    void updatePatientProfile(String userId, PatientProfileUpdateDTO request) throws ItemNotFoundException;
    UserMinimalInfoDTO getUserMinimalInfo(String userId) throws ItemNotFoundException;
    Integer patientCount();
    List<Patient> getAll();
}

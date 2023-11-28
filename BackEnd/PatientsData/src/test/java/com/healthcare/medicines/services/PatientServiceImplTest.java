package com.healthcare.medicines.services;
import com.healthcare.patients.exceptions.CustomException;
import com.healthcare.patients.exceptions.InternalCommunicationException;
import com.healthcare.patients.exceptions.ItemNotFoundException;
import com.healthcare.patients.models.*;
import com.healthcare.patients.entity.Patient;
import com.healthcare.patients.repository.PatientRepository;
import com.healthcare.patients.network.AccountCreateRequester;
import com.healthcare.patients.network.PhoneNoUpdateRequester;
import com.healthcare.patients.services.implemenatations.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private AccountCreateRequester accountCreateRequester;

    @Mock
    private PhoneNoUpdateRequester phoneNoUpdateRequester;

    @InjectMocks
    private PatientServiceImpl patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetPatientBio_Success() throws ItemNotFoundException {
        String userId = "PSF1";
        Patient patient = new Patient();
        when(patientRepository.findById(userId)).thenReturn(java.util.Optional.of(patient));
        PatientBioDTO bioDTO = patientService.getPatientBio(userId);
        assertEquals(patient.getUserId(), bioDTO.getPatientId());
    }
    @Test
    void testGetMinimalInfo_Success() throws ItemNotFoundException {
        String userId = "PSF1";
        Patient patient = new Patient();
        patient.setFirstName("Feeham");
        when(patientRepository.findById(userId)).thenReturn(java.util.Optional.of(patient));
        UserMinimalInfoDTO bioDTO = patientService.getUserMinimalInfo(userId);
        assertEquals(patient.getFirstName(), bioDTO.getFirstName());
    }
}
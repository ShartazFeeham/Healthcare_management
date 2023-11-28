package com.healthcare.medicines.controllers;

import com.healthcare.patients.controller.PatientController;
import com.healthcare.patients.entity.Patient;
import com.healthcare.patients.exceptions.CustomException;
import com.healthcare.patients.exceptions.ItemNotFoundException;
import com.healthcare.patients.models.*;
import com.healthcare.patients.services.interfaces.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PatientControllerTest {

    @Mock
    private PatientService patientService;
    @InjectMocks
    private PatientController patientController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testGetPatientBio_Success() throws ItemNotFoundException {
        String userId = "PSF1";
        PatientBioDTO patientBioDTO = new PatientBioDTO();
        when(patientService.getPatientBio(userId)).thenReturn(patientBioDTO);
        ResponseEntity<PatientBioDTO> response = patientController.getPatientBio(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patientBioDTO, response.getBody());
    }

    @Test
    void testGetPatientHealth_Success() throws CustomException {
        String userId = "PSF1";
        PatientHealthDTO patientHealthDTO = new PatientHealthDTO();
        when(patientService.getPatientHealth(userId)).thenReturn(patientHealthDTO);
        ResponseEntity<PatientHealthDTO> response = patientController.getPatientHealth(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patientHealthDTO, response.getBody());
    }

    @Test
    void testGetUserMinimalInfo_Success() throws ItemNotFoundException {
        String userId = "PSF1";
        UserMinimalInfoDTO minimalInfoDTO = new UserMinimalInfoDTO();
        when(patientService.getUserMinimalInfo(userId)).thenReturn(minimalInfoDTO);
        ResponseEntity<UserMinimalInfoDTO> response = patientController.getMinimalInfo(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(minimalInfoDTO, response.getBody());
    }

    @Test
    void testGetTotalCount_Success() {
        int totalCount = 10;
        when(patientService.patientCount()).thenReturn(totalCount);
        ResponseEntity<Integer> response = patientController.getTotalCount();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(totalCount, response.getBody());
    }

    @Test
    void testGetAllPatients_Success() {
        List<Patient> patients = Arrays.asList(new Patient(), new Patient());
        when(patientService.getAll()).thenReturn(patients);
        ResponseEntity<List<Patient>> response = patientController.getAllPatients();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patients, response.getBody());
    }

    @Test
    void testGetAllPatients_EmptyList() {
        when(patientService.getAll()).thenReturn(Collections.emptyList());
        ResponseEntity<List<Patient>> response = patientController.getAllPatients();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    void testGetPatientBio_ItemNotFoundException() throws ItemNotFoundException {
        String userId = "nonexistent";
        when(patientService.getPatientBio(userId)).thenThrow(new ItemNotFoundException("Patient not found", "PSF1"));
        assertThrows(ItemNotFoundException.class, () -> {
            patientController.getPatientBio(userId);
        });
    }
}

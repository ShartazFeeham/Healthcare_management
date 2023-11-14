package com.healthcare.patientsdata.service.interfaces;

import com.healthcare.patientsdata.entity.Doctor;

import java.util.List;

public interface SearchService {
    List<Doctor> searchBySpecialization(String specialization);
    List<Doctor> searchDoctor(String key);
}

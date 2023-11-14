package com.healthcare.patientsdata.service.interfaces;

import com.healthcare.patientsdata.entity.Doctor;

import java.util.List;

public interface UtilityService {
    List<String> getSpecializations();
    void verifyDoctor(String userId, boolean status);
    List<Doctor> getPendingDoctors();
}

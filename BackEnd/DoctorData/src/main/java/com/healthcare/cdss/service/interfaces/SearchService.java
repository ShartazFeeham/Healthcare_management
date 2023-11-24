package com.healthcare.cdss.service.interfaces;

import com.healthcare.cdss.entity.Doctor;

import java.util.List;

public interface SearchService {
    List<Doctor> searchBySpecialization(String specialization);
}

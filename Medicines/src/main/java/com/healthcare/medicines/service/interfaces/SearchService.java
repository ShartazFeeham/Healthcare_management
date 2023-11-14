package com.healthcare.medicines.service.interfaces;

import com.healthcare.medicines.entity.Doctor;

import java.util.List;

public interface SearchService {
    List<Doctor> searchBySpecialization(String specialization);
}

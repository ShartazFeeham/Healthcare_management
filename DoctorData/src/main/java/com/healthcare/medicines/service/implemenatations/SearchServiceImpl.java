package com.healthcare.medicines.service.implemenatations;

import com.healthcare.medicines.entity.Doctor;
import com.healthcare.medicines.repository.DoctorRepository;
import com.healthcare.medicines.service.interfaces.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public SearchServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Doctor> searchBySpecialization(String specialization) {
        return doctorRepository.findBySpecializationsContainingIgnoreCase(specialization);
    }
}

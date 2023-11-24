package com.healthcare.cdss.service.implemenatations;

import com.healthcare.cdss.entity.Doctor;
import com.healthcare.cdss.repository.DoctorRepository;
import com.healthcare.cdss.service.interfaces.SearchService;
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

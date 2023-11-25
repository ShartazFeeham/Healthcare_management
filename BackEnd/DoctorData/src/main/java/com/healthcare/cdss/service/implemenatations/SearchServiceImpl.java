package com.healthcare.cdss.service.implemenatations;

import com.healthcare.cdss.entity.Doctor;
import com.healthcare.cdss.models.FilteredBySpecDTO;
import com.healthcare.cdss.repository.DoctorRepository;
import com.healthcare.cdss.service.interfaces.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public SearchServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<FilteredBySpecDTO> searchBySpecialization(String specialization) {
        if(specialization.equalsIgnoreCase("all"))  return doctorRepository.findAll().stream().map(doc -> {
            return new FilteredBySpecDTO(doc.getUserId(), doc.getFirstName(), doc.getLastName(), doc.getProfilePhoto(), doc.getExperience().toString(), doc.getSpecializations(), doc.getGender());
        }).collect(Collectors.toList());
        return doctorRepository.findBySpecializationsContainingIgnoreCase(specialization).stream().map(doc -> {
            return new FilteredBySpecDTO(doc.getUserId(), doc.getFirstName(), doc.getLastName(), doc.getProfilePhoto(), doc.getExperience().toString(), doc.getSpecializations(), doc.getGender());
        }).collect(Collectors.toList());
    }
}

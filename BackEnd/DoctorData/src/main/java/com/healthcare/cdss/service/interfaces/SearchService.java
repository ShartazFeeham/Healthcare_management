package com.healthcare.cdss.service.interfaces;

import com.healthcare.cdss.entity.Doctor;
import com.healthcare.cdss.models.FilteredBySpecDTO;

import java.util.List;

public interface SearchService {
    List<FilteredBySpecDTO> searchBySpecialization(String specialization);
}

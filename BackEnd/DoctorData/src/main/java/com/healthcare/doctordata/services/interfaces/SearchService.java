package com.healthcare.doctordata.services.interfaces;

import com.healthcare.doctordata.models.FilteredBySpecDTO;

import java.util.List;
import java.util.Map;

public interface SearchService {
    List<FilteredBySpecDTO> searchBySpecialization(String specialization);
    public List<Map<String, Object>> search(String keyword);
}

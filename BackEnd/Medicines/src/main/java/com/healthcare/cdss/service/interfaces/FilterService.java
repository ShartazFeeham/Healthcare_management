package com.healthcare.cdss.service.interfaces;

import com.healthcare.cdss.enums.FilterTerms;
import com.healthcare.cdss.models.MedicineListResponseDTO;

public interface FilterService {
    MedicineListResponseDTO filter(String searchTerm, FilterTerms sortType, FilterTerms expFilter, int page, int size);
}

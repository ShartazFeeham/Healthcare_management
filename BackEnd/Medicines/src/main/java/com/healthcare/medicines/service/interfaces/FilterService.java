package com.healthcare.medicines.service.interfaces;

import com.healthcare.medicines.enums.FilterTerms;
import com.healthcare.medicines.models.MedicineListResponseDTO;

public interface FilterService {
    MedicineListResponseDTO filter(String searchTerm, FilterTerms sortType, FilterTerms expFilter, int page, int size);
}

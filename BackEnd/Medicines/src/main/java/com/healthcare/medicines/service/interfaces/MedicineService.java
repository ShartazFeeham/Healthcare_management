package com.healthcare.medicines.service.interfaces;

import com.healthcare.medicines.entity.Medicine;
import com.healthcare.medicines.exceptions.ItemNotFoundException;
import com.healthcare.medicines.models.MedicineListResponseDTO;
import com.healthcare.medicines.models.MedicineRequestDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface MedicineService {
    Medicine getMedicineById(String id) throws ItemNotFoundException;
    void createMedicine(MedicineRequestDTO medicine);
    void updateMedicine(String id, MedicineRequestDTO updatedMedicine) throws ItemNotFoundException;
    void deleteMedicine(String id) throws ItemNotFoundException;
}

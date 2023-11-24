package com.healthcare.cdss.service.interfaces;

import com.healthcare.cdss.entity.Medicine;
import com.healthcare.cdss.exceptions.ItemNotFoundException;
import com.healthcare.cdss.models.MedicineRequestDTO;

public interface MedicineService {
    Medicine getMedicineById(String id) throws ItemNotFoundException;
    void createMedicine(MedicineRequestDTO medicine);
    void updateMedicine(String id, MedicineRequestDTO updatedMedicine) throws ItemNotFoundException;
    void deleteMedicine(String id) throws ItemNotFoundException;
}

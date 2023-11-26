package com.healthcare.medicines.services.interfaces;

import com.healthcare.medicines.entity.Medicine;
import com.healthcare.medicines.exceptions.ItemNotFoundException;
import com.healthcare.medicines.models.MedicineRequestDTO;

public interface MedicineService {
    Medicine getMedicineById(String id) throws ItemNotFoundException;
    void createMedicine(MedicineRequestDTO medicine);
    void updateMedicine(String id, MedicineRequestDTO updatedMedicine) throws ItemNotFoundException;
    void deleteMedicine(String id) throws ItemNotFoundException;
}

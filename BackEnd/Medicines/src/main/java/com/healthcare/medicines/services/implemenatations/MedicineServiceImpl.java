package com.healthcare.medicines.services.implemenatations;

import com.healthcare.medicines.entity.Medicine;
import com.healthcare.medicines.exceptions.ItemNotFoundException;
import com.healthcare.medicines.models.MedicineRequestDTO;
import com.healthcare.medicines.repository.MedicineRepository;
import com.healthcare.medicines.services.interfaces.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service @RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;

    @Override
    public Medicine getMedicineById(String id) throws ItemNotFoundException {
        Optional<Medicine> medicineOp = medicineRepository.findById(id);
        if(medicineOp.isEmpty()) throw new ItemNotFoundException("medicine", id);
        return medicineOp.get();
    }

    @Override
    public void createMedicine(MedicineRequestDTO medicineDTO) {
        Medicine medicine = convertToEntity(medicineDTO);
        medicine.setId(getId());
        medicineRepository.save(medicine);
    }

    private String getId() {
        Random random = new Random();
        int randomSuffix = 0;
        while(randomSuffix < 10) randomSuffix = random.nextInt(100);
        return  "MED" + (medicineRepository.count() + 1) + "" + randomSuffix;
    }

    @Override
    public void updateMedicine(String id, MedicineRequestDTO updatedMedicine) throws ItemNotFoundException {
        getMedicineById(id);
        Medicine medicine = convertToEntity(updatedMedicine);
        medicine.setId(id);
        medicineRepository.save(medicine);
    }

    @Override
    public void deleteMedicine(String id) throws ItemNotFoundException {
        getMedicineById(id);
        medicineRepository.deleteById(id);
    }

    private Medicine convertToEntity(MedicineRequestDTO medicineRequestDTO) {
        Medicine medicine = new Medicine();
        medicine.setCommercialName(medicineRequestDTO.getCommercialName());
        medicine.setMedicineName(medicineRequestDTO.getMedicineName());
        medicine.setClassification(medicineRequestDTO.getClassification());
        medicine.setDescription(medicineRequestDTO.getDescription());
        medicine.setDosageForm(medicineRequestDTO.getDosageForm());
        medicine.setStrengthVolume(medicineRequestDTO.getStrengthVolume());
        medicine.setStrengthWeight(medicineRequestDTO.getStrengthWeight());
        medicine.setWarnings(medicineRequestDTO.getWarnings());
        medicine.setAdverseEffects(medicineRequestDTO.getAdverseEffects());
        medicine.setManufacturer(medicineRequestDTO.getManufacturer());
        medicine.setNationalDrugCode(medicineRequestDTO.getNationalDrugCode());
        medicine.setPhotoUrl(medicineRequestDTO.getPhoto());

        // Expected expiration date format is in "yyyy-MM-dd"
        if (medicineRequestDTO.getExpirationDate() != null) {
            medicine.setExpirationDate(LocalDate.parse(medicineRequestDTO.getExpirationDate()));
        }

        return medicine;
    }
}

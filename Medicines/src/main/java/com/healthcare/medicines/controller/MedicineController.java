package com.healthcare.medicines.controller;

import com.healthcare.medicines.entity.Medicine;
import com.healthcare.medicines.enums.FilterTerms;
import com.healthcare.medicines.exceptions.ItemNotFoundException;
import com.healthcare.medicines.models.MedicineListResponseDTO;
import com.healthcare.medicines.models.MedicineRequestDTO;
import com.healthcare.medicines.service.interfaces.FilterService;
import com.healthcare.medicines.service.interfaces.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;
    private final FilterService filterService;

    @GetMapping
    public ResponseEntity<MedicineListResponseDTO> filterMedicines(
            @RequestParam(value = "searchTerm", required = false) String searchTerm,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam FilterTerms sort, @RequestParam FilterTerms expiration) {

        MedicineListResponseDTO medicines = filterService.filter(searchTerm, sort, expiration, page, size);
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable String id) throws ItemNotFoundException {
        Medicine medicine = medicineService.getMedicineById(id);
        return ResponseEntity.ok(medicine);
    }

    @PostMapping
    public ResponseEntity<String> createMedicine(@RequestBody MedicineRequestDTO medicineDTO) {
        medicineService.createMedicine(medicineDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Medicine created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMedicine(@PathVariable String id, @RequestBody MedicineRequestDTO updatedMedicine) throws ItemNotFoundException {
        medicineService.updateMedicine(id, updatedMedicine);
        return ResponseEntity.ok("Medicine updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedicine(@PathVariable String id) throws ItemNotFoundException {
        medicineService.deleteMedicine(id);
        return ResponseEntity.ok("Medicine deleted successfully");
    }
}

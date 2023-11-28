package com.healtcare.appointments.services.implementations;

import com.healtcare.appointments.entities.Equipment;
import com.healtcare.appointments.exception.ItemNotFoundException;
import com.healtcare.appointments.repositories.EquipmentRepository;
import com.healtcare.appointments.services.interfaces.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;

    @Override
    public void create(Equipment equipment) {
        // Generate a unique ID for the equipment
        equipment.setId(generateEquipmentId());
        // Save the equipment to the repository
        equipmentRepository.save(equipment);
    }

    private String generateEquipmentId() {
        // Generate a random suffix to ensure uniqueness
        Random random = new Random();
        int randomSuffix = 0;
        while (randomSuffix < 10) randomSuffix = random.nextInt(100);
        // Create the equipment ID using a prefix, count, and random suffix
        return "EQU" + (equipmentRepository.count() + 1) + randomSuffix;
    }

    @Override
    public Equipment read(String equipmentId) {
        // Find equipment by ID in the repository
        Optional<Equipment> equipmentOp = equipmentRepository.findById(equipmentId);
        // Throw an exception if equipment is not found
        if (equipmentOp.isEmpty()) throw new ItemNotFoundException("equipment", equipmentId);
        return equipmentOp.get();
    }

    @Override
    public void update(String equipmentId, Equipment equipment) {
        // Check if equipment with the given ID exists
        read(equipmentId);
        // Set the ID for the equipment being updated
        equipment.setId(equipmentId);
        // Save the updated equipment to the repository
        equipmentRepository.save(equipment);
    }

    @Override
    public void delete(String equipmentId) {
        // Find equipment by ID in the repository
        Equipment equipment = read(equipmentId);
        // Delete the equipment from the repository
        equipmentRepository.delete(equipment);
    }

    @Override
    public List<Equipment> getList(int page, int size) {
        // Retrieve a paginated list of equipment from the repository
        return equipmentRepository.findAll(PageRequest.of(page, size)).stream().toList();
    }
}

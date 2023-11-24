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

@Service @RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;

    @Override
    public void create(Equipment equipment) {
        equipment.setId(getId());
        equipmentRepository.save(equipment);
    }

    private String getId() {
        Random random = new Random();
        int randomSuffix = 0;
        while(randomSuffix < 10) randomSuffix = random.nextInt(100);
        return  "EQU" + (equipmentRepository.count() + 1) + randomSuffix;
    }

    @Override
    public Equipment read(String equipmentId) {
        Optional<Equipment> equipmentOp = equipmentRepository.findById(equipmentId);
        if(equipmentOp.isEmpty()) throw new ItemNotFoundException("equipment", equipmentId);
        return equipmentOp.get();
    }

    @Override
    public void update(String equipmentId, Equipment equipment) {
        read(equipmentId);
        equipment.setId(equipmentId);
        equipmentRepository.save(equipment);
    }

    @Override
    public void delete(String equipmentId) {
        Equipment equipment = read(equipmentId);
        equipmentRepository.delete(equipment);
    }

    @Override
    public List<Equipment> getList(int page, int size) {
        return equipmentRepository.findAll(PageRequest.of(page, size)).stream().toList();
    }
}

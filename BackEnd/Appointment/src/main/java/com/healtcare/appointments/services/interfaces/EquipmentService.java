package com.healtcare.appointments.services.interfaces;

import com.healtcare.appointments.entities.Equipment;

import java.util.List;

public interface EquipmentService {
    void create(Equipment equipment);
    Equipment read(String equipmentId);
    void update(String equipmentId, Equipment equipment);
    void delete(String equipmentId);
    List<Equipment> getList(int page, int size);
}

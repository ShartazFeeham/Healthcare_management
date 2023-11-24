package com.healtcare.appointments.controller;

import com.healtcare.appointments.entities.Equipment;
import com.healtcare.appointments.services.interfaces.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/equipments")
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;

    @PostMapping
    public ResponseEntity<String> createEquipment(@RequestBody Equipment equipment) {
        equipmentService.create(equipment);
        return ResponseEntity.status(HttpStatus.CREATED).body("Equipment created successfully");
    }

    @GetMapping("/{equipmentId}")
    public ResponseEntity<Equipment> getEquipment(@PathVariable String equipmentId) {
        Equipment equipment = equipmentService.read(equipmentId);
        return ResponseEntity.ok(equipment);
    }

    @PutMapping("/{equipmentId}")
    public ResponseEntity<String> updateEquipment(@PathVariable String equipmentId, @RequestBody Equipment equipment) {
        equipmentService.update(equipmentId, equipment);
        return ResponseEntity.ok("Equipment updated successfully");
    }

    @DeleteMapping("/{equipmentId}")
    public ResponseEntity<String> deleteEquipment(@PathVariable String equipmentId) {
        equipmentService.delete(equipmentId);
        return ResponseEntity.ok("Equipment deleted successfully");
    }

    @GetMapping("/list/{page}/{size}")
    public ResponseEntity<List<Equipment>> getEquipmentList(@PathVariable int page, @PathVariable int size) {
        List<Equipment> equipmentList = equipmentService.getList(page, size);
        return ResponseEntity.ok(equipmentList);
    }
}

package com.healthcare.medicines.controller;

import com.healthcare.medicines.exceptions.ItemNotFoundException;
import com.healthcare.medicines.models.DoctorRoomDTO;
import com.healthcare.medicines.service.interfaces.RoomAllocationService;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/rooms") @RequiredArgsConstructor
public class RoomAllocationController {
    private final RoomAllocationService roomAllocationService;
    @GetMapping("/list/{page}/{size}")
    public ResponseEntity<List<DoctorRoomDTO>> readDoctors(@PathVariable int page, @PathVariable int size){
        return ResponseEntity.ok(roomAllocationService.getAllAllocations(page, size));
    }
    @PostMapping("/allocate")
    public ResponseEntity<String> allocate(@RequestBody DoctorRoomDTO doctorRoomDTO) throws ItemNotFoundException {
        roomAllocationService.setRoom(doctorRoomDTO);
        return ResponseEntity.ok("Room allocation successful.");
    }
}

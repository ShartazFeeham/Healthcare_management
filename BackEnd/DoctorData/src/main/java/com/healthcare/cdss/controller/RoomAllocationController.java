package com.healthcare.cdss.controller;

import com.healthcare.cdss.exceptions.ItemNotFoundException;
import com.healthcare.cdss.models.DoctorRoomDTO;
import com.healthcare.cdss.service.interfaces.RoomAllocationService;
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

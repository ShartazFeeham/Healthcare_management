package com.healthcare.medicines.service.interfaces;

import com.healthcare.medicines.exceptions.ItemNotFoundException;
import com.healthcare.medicines.models.DoctorRoomDTO;

import java.util.List;

public interface RoomAllocationService {
    void setRoom(DoctorRoomDTO doctorRoomDTO) throws ItemNotFoundException;
    List<DoctorRoomDTO> getAllAllocations(int page, int size);
}

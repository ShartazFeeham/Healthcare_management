package com.healthcare.cdss.service.interfaces;

import com.healthcare.cdss.exceptions.ItemNotFoundException;
import com.healthcare.cdss.models.DoctorRoomDTO;

import java.util.List;

public interface RoomAllocationService {
    void setRoom(DoctorRoomDTO doctorRoomDTO) throws ItemNotFoundException;
    List<DoctorRoomDTO> getAllAllocations(int page, int size);
}

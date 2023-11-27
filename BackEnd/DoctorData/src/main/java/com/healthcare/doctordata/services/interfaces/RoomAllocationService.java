package com.healthcare.doctordata.services.interfaces;

import com.healthcare.doctordata.exceptions.ItemNotFoundException;
import com.healthcare.doctordata.models.DoctorRoomDTO;

import java.util.List;

public interface RoomAllocationService {
    void setRoom(DoctorRoomDTO doctorRoomDTO) throws ItemNotFoundException;
    List<DoctorRoomDTO> getAllAllocations(int page, int size);
    String getRoomByDoctor(String doctorId) throws ItemNotFoundException;
}

package com.healthcare.doctordata.services.implemenatations;

import com.healthcare.doctordata.entity.Doctor;
import com.healthcare.doctordata.exceptions.ItemNotFoundException;
import com.healthcare.doctordata.models.DoctorRoomDTO;
import com.healthcare.doctordata.repository.DoctorRepository;
import com.healthcare.doctordata.services.interfaces.RoomAllocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class RoomAllocaitonServiceImpl implements RoomAllocationService {

    private final DoctorRepository doctorRepository;

    @Override
    public void setRoom(DoctorRoomDTO doctorRoomDTO) throws ItemNotFoundException {
        Optional<Doctor> doctorOp = doctorRepository.findById(doctorRoomDTO.getDoctorId());
        if(doctorOp.isEmpty()) throw new ItemNotFoundException("doctor", doctorRoomDTO.getDoctorId());
        Doctor doctor = doctorOp.get();
        doctor.setRoom(doctorRoomDTO.getRoom());
        doctorRepository.save(doctor);
    }

    @Override
    public List<DoctorRoomDTO> getAllAllocations(int page, int size) {
        Page<Doctor> doctorsPage = doctorRepository.findAll(PageRequest.of(page, size));
        return doctorsPage.getContent().stream()
                .map(doc -> new DoctorRoomDTO(doc.getUserId(), doc.getFirstName() + " " + doc.getLastName(), doc.getRoom()))
                .collect(Collectors.toList());
    }

    @Override
    public String getRoomByDoctor(String doctorId) throws ItemNotFoundException {
        Optional<Doctor> doctorOp = doctorRepository.findById(doctorId);
        if(doctorOp.isEmpty()) throw new ItemNotFoundException("doctor", doctorId);
        return doctorOp.get().getRoom();
    }
}

package com.healthcare.medicines.service.interfaces;

import com.healthcare.medicines.exceptions.AccessMismatchException;
import com.healthcare.medicines.exceptions.ItemNotFoundException;
import com.healthcare.medicines.models.CreateDoctorAccountDTO;
import com.healthcare.medicines.models.ReadDoctorPersonalInfoDTO;
import com.healthcare.medicines.models.ReadDoctorProfileDTO;
import com.healthcare.medicines.models.UpdateDoctorProfileDTO;

public interface DoctorService {
    public void register(CreateDoctorAccountDTO createDoctorAccountDTO);
    ReadDoctorPersonalInfoDTO readPersonalInfo(String userId) throws ItemNotFoundException;
    ReadDoctorProfileDTO readDoctorProfileInfo(String userId) throws ItemNotFoundException;
    public void update(String userId, UpdateDoctorProfileDTO updateDoctorProfileDTO) throws AccessMismatchException, ItemNotFoundException;
    public void delete(String userId) throws ItemNotFoundException;
}

package com.healthcare.patientsdata.service.interfaces;

import com.healthcare.patientsdata.exceptions.AccessMismatchException;
import com.healthcare.patientsdata.exceptions.ItemNotFoundException;
import com.healthcare.patientsdata.models.CreateDoctorAccountDTO;
import com.healthcare.patientsdata.models.ReadDoctorPersonalInfoDTO;
import com.healthcare.patientsdata.models.ReadDoctorProfileDTO;
import com.healthcare.patientsdata.models.UpdateDoctorProfileDTO;

public interface DoctorService {
    public void register(CreateDoctorAccountDTO createDoctorAccountDTO);
    ReadDoctorPersonalInfoDTO readPersonalInfo(String userId) throws ItemNotFoundException;
    ReadDoctorProfileDTO readDoctorProfileInfo(String userId) throws ItemNotFoundException;
    public void update(String userId, UpdateDoctorProfileDTO updateDoctorProfileDTO) throws AccessMismatchException, ItemNotFoundException;
    public void delete(String userId) throws ItemNotFoundException;
}

package com.healthcare.cdss.service.interfaces;

import com.healthcare.cdss.exceptions.AccessMismatchException;
import com.healthcare.cdss.exceptions.ItemNotFoundException;
import com.healthcare.cdss.models.*;

public interface DoctorService {
    public void register(CreateDoctorAccountDTO createDoctorAccountDTO);
    ReadDoctorPersonalInfoDTO readPersonalInfo(String userId) throws ItemNotFoundException;
    ReadDoctorProfileDTO readDoctorProfileInfo(String userId) throws ItemNotFoundException;
    public void update(String userId, UpdateDoctorProfileDTO updateDoctorProfileDTO) throws AccessMismatchException, ItemNotFoundException;
    public void delete(String userId) throws ItemNotFoundException;
    UserMinimalInfoDTO getUserMinimalInfo(String userId) throws ItemNotFoundException;
    DoctorEditExistingDTO editExisting() throws ItemNotFoundException;
}

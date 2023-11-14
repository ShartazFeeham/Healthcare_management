package com.healthcare.patientsdata.service.interfaces;

import com.healthcare.patientsdata.models.CreateDoctorAccountDTO;
import com.healthcare.patientsdata.models.ReadDoctorPersonalInfoDTO;
import com.healthcare.patientsdata.models.ReadDoctorProfileDTO;
import com.healthcare.patientsdata.models.UpdateDoctorProfileDTO;

public interface DoctorService {
    public void register(CreateDoctorAccountDTO createDoctorAccountDTO);
    ReadDoctorPersonalInfoDTO readPersonalInfo(String userId);
    ReadDoctorProfileDTO readDoctorProfileInfo(String userId);
    public void update(UpdateDoctorProfileDTO updateDoctorProfileDTO);
    public void delete(String userId);
}

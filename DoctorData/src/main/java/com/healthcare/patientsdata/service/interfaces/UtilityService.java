package com.healthcare.patientsdata.service.interfaces;

import com.healthcare.patientsdata.entity.Doctor;
import com.healthcare.patientsdata.exceptions.ItemNotFoundException;

import java.util.List;

public interface UtilityService {
    List<String> getSpecializations();
}

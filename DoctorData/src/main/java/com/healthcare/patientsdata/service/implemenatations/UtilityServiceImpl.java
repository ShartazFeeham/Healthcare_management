package com.healthcare.patientsdata.service.implemenatations;

import com.healthcare.patientsdata.entity.Doctor;
import com.healthcare.patientsdata.enums.Specializations;
import com.healthcare.patientsdata.exceptions.ItemNotFoundException;
import com.healthcare.patientsdata.repository.DoctorRepository;
import com.healthcare.patientsdata.service.interfaces.UtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class UtilityServiceImpl implements UtilityService {

    private final DoctorRepository doctorRepository;

    @Override
    public List<String> getSpecializations() {
        return Specializations.LIST;
    }
}

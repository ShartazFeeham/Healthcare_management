package com.healthcare.doctordata.services.implemenatations;

import com.healthcare.doctordata.enums.Specializations;
import com.healthcare.doctordata.repository.DoctorRepository;
import com.healthcare.doctordata.services.interfaces.UtilityService;
import lombok.RequiredArgsConstructor;
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

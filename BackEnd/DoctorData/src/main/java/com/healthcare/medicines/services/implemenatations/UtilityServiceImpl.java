package com.healthcare.medicines.services.implemenatations;

import com.healthcare.medicines.enums.Specializations;
import com.healthcare.medicines.repository.DoctorRepository;
import com.healthcare.medicines.services.interfaces.UtilityService;
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

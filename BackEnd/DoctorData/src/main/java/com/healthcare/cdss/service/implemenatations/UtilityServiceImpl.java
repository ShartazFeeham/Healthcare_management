package com.healthcare.cdss.service.implemenatations;

import com.healthcare.cdss.enums.Specializations;
import com.healthcare.cdss.repository.DoctorRepository;
import com.healthcare.cdss.service.interfaces.UtilityService;
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

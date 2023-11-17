package com.healtcare.appointments.services.implementations;

import com.healtcare.appointments.models.ScheduleGetDTO;
import com.healtcare.appointments.models.ScheduleSetDTO;
import com.healtcare.appointments.services.interfaces.ScheduleService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Override
    public void setSchedule(ScheduleSetDTO scheduleDTO) {

    }

    @Override
    public ScheduleGetDTO getScheduleByDateAndDoctorId(String date, String doctorId) {
        return null;
    }

    @Override
    public List<LocalDate> getDatesByDoctorId(String doctorId) {
        return null;
    }

    @Override
    public void delayTime(Integer timeInMinutes) {

    }
}

package com.healtcare.appointments.services.interfaces;

import com.healtcare.appointments.models.ScheduleGetDTO;
import com.healtcare.appointments.models.ScheduleSetDTO;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    /**
     * Works for create, update and delete.
     * Create - if the date and doctor id doesn't already exist then create.
     * Update - if the date and doctor id already exists and no conflicts then update.
     * Delete - if the date and doctor id exists and all shifts are 0 (doctor not available) then delete
     */
    public void setSchedule(ScheduleSetDTO scheduleDTO);
    public ScheduleGetDTO getScheduleByDateAndDoctorId(String date, String doctorId);
    public List<LocalDate> getDatesByDoctorId(String doctorId);
    public void delayTime(Integer timeInMinutes);
}

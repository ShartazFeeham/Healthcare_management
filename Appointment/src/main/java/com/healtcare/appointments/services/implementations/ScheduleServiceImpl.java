package com.healtcare.appointments.services.implementations;

import com.healtcare.appointments.entities.Schedule;
import com.healtcare.appointments.models.AvailabilityDTO;
import com.healtcare.appointments.models.ScheduleGetDTO;
import com.healtcare.appointments.models.ScheduleSetDTO;
import com.healtcare.appointments.repositories.ScheduleRepository;
import com.healtcare.appointments.services.interfaces.ScheduleService;
import com.healtcare.appointments.utilities.token.IDExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public void setSchedule(ScheduleSetDTO scheduleDTO) {
        // Check if the schedule for the given date and doctorId already exists
        Schedule existingSchedule = scheduleRepository.findByDateAndDoctorId(scheduleDTO.getDate(), IDExtractor.getUserID())
                .orElse(new Schedule());

        // Update existing schedule or create a new one
        if (existingSchedule.getId() != null) {
            // Existing schedule found, update if there are no conflicts
            if (noConflicts(existingSchedule, scheduleDTO)) {
                updateSchedule(existingSchedule, scheduleDTO);
            } else {
                // Handle conflicts, you may throw an exception or take appropriate action
                throw new RuntimeException("Conflicts found in the schedule. Cannot update.");
            }
        } else {
            // No existing schedule found, create a new one
            createSchedule(scheduleDTO);
        }
    }

    @Override
    public ScheduleGetDTO getScheduleByDateAndDoctorId(String date, String doctorId) {
        // Retrieve the schedule by date and doctorId
        Schedule schedule = scheduleRepository.findByDateAndDoctorId(LocalDate.parse(date), doctorId)
                .orElse(new Schedule()); // Handle non-existent schedule if necessary

        // Convert the entity to DTO
        return convertToScheduleGetDTO(schedule);
    }

    @Override
    public List<LocalDate> getDatesByDoctorId(String doctorId) {
        // Retrieve distinct dates for the given doctorId
        return scheduleRepository.findDistinctDatesByDoctorId(doctorId);
    }

    @Override
    public void delayTime(Integer timeInMinutes) {
        // Implement logic to delay time in the schedule (if needed)
    }

    // Private method to check for conflicts in the schedule
    private boolean noConflicts(Schedule existingSchedule, ScheduleSetDTO scheduleDTO) {
        // Implement logic to check for conflicts, e.g., overlapping shifts
        // For simplicity, let's assume no conflicts for now.
        return true;
    }

    // Private method to update the existing schedule
    private void updateSchedule(Schedule existingSchedule, ScheduleSetDTO scheduleDTO) {
        // Update shifts and capacities
        existingSchedule.setMorningShift(scheduleDTO.getMorning());
        existingSchedule.setAfternoonShift(scheduleDTO.getAfterNoon());
        existingSchedule.setEveningShift(scheduleDTO.getEvening());

        existingSchedule.setMorningCapacity(scheduleDTO.getMorningCapacity());
        existingSchedule.setAfternoonCapacity(scheduleDTO.getAfterNoonCapacity());
        existingSchedule.setEveningCapacity(scheduleDTO.getEveningCapacity());

        // Save the updated schedule
        scheduleRepository.save(existingSchedule);
    }

    // Private method to create a new schedule
    private void createSchedule(ScheduleSetDTO scheduleDTO) {
        Schedule newSchedule = new Schedule();
        newSchedule.setDoctorId(IDExtractor.getUserID());
        newSchedule.setDate(scheduleDTO.getDate());
        newSchedule.setMorningShift(scheduleDTO.getMorning());
        newSchedule.setAfternoonShift(scheduleDTO.getAfterNoon());
        newSchedule.setEveningShift(scheduleDTO.getEvening());

        newSchedule.setMorningCapacity(scheduleDTO.getMorningCapacity());
        newSchedule.setAfternoonCapacity(scheduleDTO.getAfterNoonCapacity());
        newSchedule.setEveningCapacity(scheduleDTO.getEveningCapacity());

        // Save the new schedule
        scheduleRepository.save(newSchedule);
    }

    // Private method to convert Schedule entity to ScheduleGetDTO
    private ScheduleGetDTO convertToScheduleGetDTO(Schedule schedule) {
        ScheduleGetDTO scheduleGetDTO = new ScheduleGetDTO();
        scheduleGetDTO.setMorning(schedule.getMorningShift());
        scheduleGetDTO.setMorningAvailability(new AvailabilityDTO(/* Set availability details if needed */));
        scheduleGetDTO.setAfterNoon(schedule.getAfternoonShift());
        scheduleGetDTO.setAfternoonAvailability(new AvailabilityDTO(/* Set availability details if needed */));
        scheduleGetDTO.setEvening(schedule.getEveningShift());
        scheduleGetDTO.setEveningAvailability(new AvailabilityDTO(/* Set availability details if needed */));
        return scheduleGetDTO;
    }
}

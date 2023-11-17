package com.healtcare.appointments.services.implementations;

import com.healtcare.appointments.entities.Appointment;
import com.healtcare.appointments.models.AppointmentListDTO;
import com.healtcare.appointments.models.AppointmentRequestDTO;
import com.healtcare.appointments.repositories.AppointmentRepository;
import com.healtcare.appointments.services.interfaces.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment createAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        // Perform any necessary validation or business logic before creating the appointment

        // Create an Appointment entity from the DTO
        Appointment appointment = new Appointment();
        appointment.setDoctorId(appointmentRequestDTO.getDoctorId());
        appointment.setDate(appointmentRequestDTO.getDate().toString());
        // Set other fields from the DTO

        // Set default values or perform additional logic if needed

        // Save the appointment to the database
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getAppointment(String appointmentId) {
        // Retrieve the appointment by ID
        return appointmentRepository.findById(Long.valueOf(appointmentId))
                .orElse(null); // handle non-existent appointment case if necessary
    }

    @Override
    public void cancelAppointment(String appointmentId) {
        // Retrieve the appointment by ID
        Appointment appointment = appointmentRepository.findById(Long.valueOf(appointmentId))
                .orElseThrow(() -> new RuntimeException("Appointment not found")); // handle non-existent appointment case if necessary

        // Update the appointment's canceled status
        appointment.setCancelled(true);

        // Save the updated appointment
        appointmentRepository.save(appointment);
    }

    @Override
    public List<AppointmentListDTO> getCompleteAppointmentsByPatient(String patientId) {
        // Retrieve complete (not cancelled) appointments for the patient
        List<Appointment> appointments = appointmentRepository.findByPatientIdAndCancelled(patientId, false);

        // Convert entities to DTOs
        return appointments.stream()
                .map(appointment -> new AppointmentListDTO(appointment.getId().toString(), appointment.getAppointmentTime().toString()))
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentListDTO> getUpcomingAppointmentsByPatient(String patientId) {
        // Retrieve upcoming (not cancelled) appointments for the patient
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<Appointment> appointments = appointmentRepository.findByPatientIdAndCancelledAndAppointmentTimeAfter(patientId, false, currentDateTime);

        // Convert entities to DTOs
        return appointments.stream()
                .map(appointment -> new AppointmentListDTO(appointment.getId().toString(), appointment.getAppointmentTime().toString()))
                .collect(Collectors.toList());
    }

    @Override
    public Integer countAppointments(String doctorId, String date, String shift) {
        // Convert date string to LocalDate
        LocalDate localDate = LocalDate.parse(date);

        // Count the number of appointments for the doctor on the specified date and shift
        return appointmentRepository.countByDoctorIdAndDateAndShift(doctorId, localDate, shift);
    }

    @Override
    public Integer countAppointmentCapacity(String doctorId, String date, String shift) {
        // Convert date string to LocalDate
        LocalDate localDate = LocalDate.parse(date);

        // Count the available appointment capacity for the doctor on the specified date and shift
        return appointmentRepository.countByDoctorIdAndDateAndShiftAndCancelled(doctorId, localDate, shift, false);
    }

    @Override
    public Integer countTotalAppointmentByDoctorId(String doctorId) {
        // Count the total number of appointments for the doctor
        return appointmentRepository.countByDoctorId(doctorId);
    }

    @Override
    public boolean checkAppointmentValidity(String appointmentId) {
        // Check if an appointment with the given ID exists
        return appointmentRepository.existsById(Long.valueOf(appointmentId));
    }
}

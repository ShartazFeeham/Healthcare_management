package com.healtcare.appointments;

import com.healtcare.appointments.services.implementations.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AppointmentsApplication {
	public static void main(String[] args) {
		SpringApplication.run(AppointmentsApplication.class, args);
	}
}
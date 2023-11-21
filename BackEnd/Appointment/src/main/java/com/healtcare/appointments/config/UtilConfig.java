package com.healtcare.appointments.config;

import com.healtcare.appointments.config.SpringApplicationContext;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UtilConfig {
    @Bean
    public SpringApplicationContext springApplicationContext(){
        return new SpringApplicationContext();
    }
}

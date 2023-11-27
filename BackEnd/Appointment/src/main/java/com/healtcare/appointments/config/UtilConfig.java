package com.healtcare.appointments.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilConfig {
    @Bean
    public SpringApplicationContext springApplicationContext(){
        return new SpringApplicationContext();
    }
}

//package com.healthcare.integration.config;
//
//import jakarta.annotation.PostConstruct;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AppConfig {
//
//    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);
//
//    @Value("${email.smtpEmailOrigin}")
//    private String smtpEmailOrigin;
//
//    @PostConstruct
//    public void init() {
//        logger.info("SMTP Email Origin: {}", smtpEmailOrigin);
//    }
//}
//

package com.healthcare.integration.controller;

import com.healthcare.integration.interfaces.EmailService;
import com.healthcare.integration.model.EmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;
    @PostMapping("/v1/email/send")
    public ResponseEntity<String> sendEmailSMTP(@RequestBody EmailRequest emailRequest){
        emailService.sendEmailSMTP(emailRequest);
        return ResponseEntity.ok("Email sent.");
    }
    @PostMapping("/v2/email/send")
    public ResponseEntity<String> sendEmailAzure(@RequestBody EmailRequest emailRequest){
        emailService.sendEmailAzure(emailRequest);
        return ResponseEntity.ok("Email sent.");
    }
}

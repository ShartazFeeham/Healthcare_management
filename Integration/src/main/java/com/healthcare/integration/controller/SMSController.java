package com.healthcare.integration.controller;

import com.ladder.IntegrationService.interfaces.SMSService;
import com.ladder.IntegrationService.model.SMSRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/sms")
@RequiredArgsConstructor
public class SMSController {
    private final SMSService smsService;
    @PostMapping("/send")
    public ResponseEntity<String> processSMS(@RequestBody SMSRequest smsRequest){
        String status = smsService.sendSms(smsRequest);
        return ResponseEntity.ok(status);
    }
}

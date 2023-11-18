package com.healthcare.integration.controller;

import com.healthcare.integration.interfaces.FCMService;
import com.healthcare.integration.model.FCMRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/push-notification")
@RequiredArgsConstructor
public class FCMController {
    private final FCMService fcmService;
    @PostMapping("/send")
    public ResponseEntity<String> sendPushNotification(@RequestBody FCMRequest fcmRequest){
        fcmService.sendFCMNotification(fcmRequest);
        return ResponseEntity.ok("Notification sent.");
    }
}

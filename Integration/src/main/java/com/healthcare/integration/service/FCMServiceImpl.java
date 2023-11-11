package com.healthcare.integration.service;

import com.healthcare.integration.interfaces.FCMService;
import com.healthcare.integration.model.FCMRequest;
import com.healthcare.integration.utility.constants.FCMConstants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FCMServiceImpl implements FCMService{
    public void sendFCMNotification(FCMRequest fcmRequest) {
        // Create HTTP headers with content type and authorization
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "key=" + FCMConstants.FCM_SERVER_KEY);

        // Create the HTTP entity with the payload and headers
        HttpEntity<FCMRequest> entity = new HttpEntity<>(fcmRequest, headers);

        // Send the POST request
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(FCMConstants.FCM_API_URL, entity, String.class);

        // Handle the response, e.g., check for success and error handling
        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("FCM Notification sent successfully");
        } else {
            System.err.println("Failed to send FCM Notification. Response: " + response.getBody());
        }
    }
}

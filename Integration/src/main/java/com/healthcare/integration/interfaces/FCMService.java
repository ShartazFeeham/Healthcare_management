package com.healthcare.integration.interfaces;

import com.ladder.IntegrationService.model.FCMRequest;

public interface FCMService {
    public void sendFCMNotification(FCMRequest fcmRequest);
}

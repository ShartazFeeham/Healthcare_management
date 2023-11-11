package com.healthcare.integration.interfaces;

import com.healthcare.integration.model.FCMRequest;

public interface FCMService {
    public void sendFCMNotification(FCMRequest fcmRequest);
}

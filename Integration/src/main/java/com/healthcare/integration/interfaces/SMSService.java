package com.healthcare.integration.interfaces;

import com.ladder.IntegrationService.model.SMSRequest;

public interface SMSService {
    public String sendSms(SMSRequest request);
}

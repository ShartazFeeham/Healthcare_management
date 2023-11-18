package com.healthcare.integration.interfaces;

import com.healthcare.integration.model.SMSRequest;

public interface SMSService {
    public String sendSms(SMSRequest request);
}

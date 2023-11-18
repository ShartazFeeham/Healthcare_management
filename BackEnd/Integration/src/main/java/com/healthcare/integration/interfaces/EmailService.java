package com.healthcare.integration.interfaces;

import com.healthcare.integration.model.EmailRequest;

public interface EmailService {
    public void sendEmailSMTP(EmailRequest emailRequest);
    public void sendEmailAzure(EmailRequest emailRequest);
}

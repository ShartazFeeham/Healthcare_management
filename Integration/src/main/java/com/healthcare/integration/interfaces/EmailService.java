package com.healthcare.integration.interfaces;

import com.ladder.IntegrationService.model.EmailRequest;

public interface EmailService {
    public void sendEmailSMTP(EmailRequest emailRequest);
    public void sendEmailAzure(EmailRequest emailRequest);
}

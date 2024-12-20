package com.healthcare.integration.service;

import com.azure.communication.email.EmailClient;
import com.azure.communication.email.EmailClientBuilder;
import com.azure.communication.email.models.EmailMessage;
import com.azure.communication.email.models.EmailSendResult;
import com.azure.communication.email.models.EmailSendStatus;
import com.azure.core.util.polling.LongRunningOperationStatus;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.SyncPoller;
import com.healthcare.integration.interfaces.EmailService;
import com.healthcare.integration.model.EmailRequest;
import com.healthcare.integration.utility.constants.EmailConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    @Override
    public void sendEmailSMTP(EmailRequest emailRequest){

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(EmailConstants.SMTP_EMAIL_ORIGIN);
        mail.setTo(emailRequest.getTo());
        mail.setSubject(emailRequest.getSubject());
        mail.setText(emailRequest.getMessage());

        mailSender.send(mail);
    }

    @Override
    public void sendEmailAzure(EmailRequest emailRequest) {

        String connectionString = EmailConstants.AZURE_EMAIL_CONNECTION_STRING;
        EmailClient emailClient = new EmailClientBuilder()
                .connectionString(connectionString)
                .buildClient();

        EmailMessage message = new EmailMessage()
                .setSenderAddress(EmailConstants.AZURE_EMAIL_ORIGIN)
                .setToRecipients(emailRequest.getTo())
                .setSubject(emailRequest.getSubject())
                .setBodyPlainText(emailRequest.getMessage());
        try{
            SyncPoller<EmailSendResult, EmailSendResult> poller = emailClient.beginSend(message, null);
            PollResponse<EmailSendResult> pollResponse = null;
            Duration timeElapsed = Duration.ofSeconds(0);
            Duration POLLER_WAIT_TIME = Duration.ofSeconds(10);

            while (pollResponse == null || pollResponse.getStatus() == LongRunningOperationStatus.NOT_STARTED || pollResponse.getStatus() == LongRunningOperationStatus.IN_PROGRESS) {
                pollResponse = poller.poll();
                System.out.println("Email send poller status: " + pollResponse.getStatus());
                Thread.sleep(POLLER_WAIT_TIME.toMillis());
                timeElapsed = timeElapsed.plus(POLLER_WAIT_TIME);
                if (timeElapsed.compareTo(POLLER_WAIT_TIME.multipliedBy(18)) >= 0) {
                    throw new RuntimeException("Polling timed out.");
                }
            }

            if (poller.getFinalResult().getStatus() == EmailSendStatus.SUCCEEDED) {
                System.out.printf("Successfully sent the email (operation id: %s)", poller.getFinalResult().getId());
            }
            else {
                throw new RuntimeException(poller.getFinalResult().getError().getMessage());
            }
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}

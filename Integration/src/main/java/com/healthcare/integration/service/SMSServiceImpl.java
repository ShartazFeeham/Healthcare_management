package com.healthcare.integration.service;

import com.healthcare.integration.interfaces.SMSService;
import com.healthcare.integration.model.SMSRequest;
import com.healthcare.integration.utility.constants.TwilioConstants;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class SMSServiceImpl implements SMSService{

    public SMSServiceImpl(){
        twilioSetup();
    }

    // Setup local Twilio authentication
    private void twilioSetup() {
        Twilio.init(TwilioConstants.ACCOUNT_SID, TwilioConstants.AUTH_TOKEN);
    }

    // Create a new message instance and send the message. Status is an enum, There is a lot of success cases
    // however there is only one failed case that is "failed"
    // usually always returns "queued" status and since the server is slow, there is no way to check actual status
    public String sendSms(SMSRequest request) {
        Message message = Message.creator(
                        new PhoneNumber(request.getReceiverNumber()),
                        new PhoneNumber(TwilioConstants.OUTGOING_SMS_NUMBER),
                        request.getMessageBody())
                .create();
        return message.getStatus().toString();
    }
}
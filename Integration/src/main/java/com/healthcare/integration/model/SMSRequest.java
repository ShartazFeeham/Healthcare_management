package com.healthcare.integration.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @RequiredArgsConstructor
public class SMSRequest {
    private String receiverNumber;
    private String messageBody;
}
package com.healthcare.integration.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter @Getter @RequiredArgsConstructor
public class EmailRequest {
    private String to;
    private String subject;
    private String message;
}

package com.healthcare.integration.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @RequiredArgsConstructor
public class FCMRequest {
    private String to;
    private FCMNotification notification;
}
package com.healthcare.integration.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @RequiredArgsConstructor
public class FCMNotification {
    private String body;
    private String title;
    private String subtitle;
}
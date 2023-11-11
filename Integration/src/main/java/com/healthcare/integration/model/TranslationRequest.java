package com.healthcare.integration.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @RequiredArgsConstructor
public class TranslationRequest {
    private String from;
    private String to;
    private String text;
}

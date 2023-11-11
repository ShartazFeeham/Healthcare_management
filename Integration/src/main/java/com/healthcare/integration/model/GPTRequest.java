package com.healthcare.integration.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class GPTRequest {

    private String model;
    private List<GPTMessage> messages;

    public GPTRequest(String model, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new GPTMessage("user",prompt));
    }
}

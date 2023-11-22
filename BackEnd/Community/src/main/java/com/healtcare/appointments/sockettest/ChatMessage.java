package com.healtcare.appointments.sockettest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ChatMessage {

    private TrayIcon.MessageType type;
    private String content;
    private String sender;

    // getters and setters
}
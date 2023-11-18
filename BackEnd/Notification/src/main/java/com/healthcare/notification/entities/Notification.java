package com.healthcare.notification.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter @RequiredArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // References
    private Long notificationId;
    private String userId;
    // Notification properties
    private String title;
    private String prefix;
    private String text;
    private String suffix;
    private String url;
    private String photoUrl;
    private LocalDateTime timeCreate;
    private boolean seen;
    private String type;
}

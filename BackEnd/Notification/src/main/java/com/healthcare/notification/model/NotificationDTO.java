package com.healthcare.notification.model;

import com.healthcare.notification.entities.Notification;
import com.healthcare.notification.utilities.TimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor
public class NotificationDTO {
    private Long notificationId;
    private String userId;
    private String title;
    private String prefix;
    private String text;
    private String suffix;
    private String url;
    private String photoUrl;
    private String timeCreate;
    private boolean seen;
    private String type;
}

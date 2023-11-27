package com.healtcare.community.network;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class NotificationRequest {
    private String userId;
    private String prefix;
    private String title;
    private String url;
    private String text;
    private String suffix;
    private String photoUrl;
    private String type;
}
package com.healthcare.notification.network;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class PushRequestDTO {
    private String to;
    private Notification notification;

    public PushRequestDTO(String to, String body, String title, String subtitle){
        this.to = to;
        this.notification = new Notification(body, title, subtitle);
    }

    @Getter @Setter @AllArgsConstructor
    public static class Notification {
        private String body;
        private String title;
        private String subtitle;
    }
}

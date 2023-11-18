package com.healthcare.notification.service.interfaces;

import com.healthcare.notification.entities.Notification;

public interface NotifyService {
    public void send(Notification notification);
}

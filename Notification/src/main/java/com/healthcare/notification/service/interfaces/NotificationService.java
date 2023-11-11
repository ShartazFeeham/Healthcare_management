package com.healthcare.notification.service.interfaces;
import com.healthcare.notification.entities.Notification;
import com.healthcare.notification.exceptions.AccessMismatchException;
import com.healthcare.notification.exceptions.ItemNotFoundException;

import java.util.List;

public interface NotificationService {
    void create(Notification notification);
    List<Notification> getAllByUserId(String userId, int itemCount, int pageNo);
    void setSeen(Long notificationId, String userId) throws ItemNotFoundException, IllegalAccessException, AccessMismatchException;
    void delete(Long notificationId) throws ItemNotFoundException;
}
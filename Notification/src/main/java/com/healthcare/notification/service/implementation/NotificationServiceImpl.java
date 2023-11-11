package com.healthcare.notification.service.implementation;

import com.healthcare.notification.entities.Notification;
import com.healthcare.notification.exceptions.AccessMismatchException;
import com.healthcare.notification.exceptions.ItemNotFoundException;
import com.healthcare.notification.repository.NotificationRepository;
import com.healthcare.notification.service.interfaces.NotificationService;
import com.healthcare.notification.service.interfaces.NotifyService;
import com.healthcare.notification.service.interfaces.PreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final PreferenceService preferenceService;
    private final NotifyService notifyService;

    @Override
    public void create(Notification notification) {
        notification.setTimeCreate(LocalDateTime.now());
        notificationRepository.save(notification);
        notifyService.send(notification);
    }

    @Override
    public List<Notification> getAllByUserId(String userId, int itemCount, int pageNo) {
        int offset = 0;
        Pageable pageable = PageRequest.of(offset, itemCount);
        Page<Notification> notificationPage = notificationRepository.findByUserId(userId, pageable);
        return notificationPage.getContent();
    }

    @Override
    public void setSeen(Long notificationId, String userId) throws ItemNotFoundException, AccessMismatchException {
        Optional<Notification> notificationOp = notificationRepository.findById(notificationId);
        if (notificationOp.isEmpty()) {
            throw new ItemNotFoundException("notification", String.valueOf(notificationId));
        }
        Notification notification = notificationOp.get();
        if (!notification.getUserId().equals(userId)) {
            throw new AccessMismatchException("The requested user doesn't own the notification");
        }
        notification.setSeen(true);
        notificationRepository.save(notification);
    }

    @Override
    public void delete(Long notificationId) throws ItemNotFoundException {
        Optional<Notification> notificationOp = notificationRepository.findById(notificationId);
        notificationOp.ifPresent(notificationRepository::delete);
        if (notificationOp.isEmpty()) {
            throw new ItemNotFoundException("notification", String.valueOf(notificationId));
        }
    }
}

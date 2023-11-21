package com.healthcare.notification.service.implementation;

import com.healthcare.notification.entities.Notification;
import com.healthcare.notification.entities.Preference;
import com.healthcare.notification.exceptions.AccessMismatchException;
import com.healthcare.notification.exceptions.ItemNotFoundException;
import com.healthcare.notification.network.EmailGetter;
import com.healthcare.notification.repository.NotificationRepository;
import com.healthcare.notification.service.interfaces.NotificationService;
import com.healthcare.notification.service.interfaces.NotifyService;
import com.healthcare.notification.service.interfaces.PreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
    private final EmailGetter emailGetter;

    @Override
    public void create(Notification notification) throws ItemNotFoundException {
        notification.setTimeCreate(LocalDateTime.now());
        verifyUser(notification.getUserId());
        notificationRepository.save(notification);
        notifyService.send(notification);
    }

    @Override
    public List<Notification> getAllByUserId(String userId, int itemCount, int pageNo) {
        int offset = pageNo - 1;
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
    public void delete(Long notificationId, String userId) throws ItemNotFoundException, AccessMismatchException {
        Optional<Notification> notificationOp = notificationRepository.findById(notificationId);
        if (notificationOp.isEmpty()) {
            throw new ItemNotFoundException("notification", String.valueOf(notificationId));
        }
        Notification notification = notificationOp.get();
        if(!notification.getUserId().equals(userId))
            throw new AccessMismatchException
                    ("An user doesn't have privilege to delete someone else's notification.", HttpStatus.FORBIDDEN);
        notificationOp.ifPresent(notificationRepository::delete);
    }

    @Override
    public Integer getUnseenCount(String userId) throws ItemNotFoundException {
        Integer unseenCount = notificationRepository.countByUserIdAndSeenFalse(userId);
        if (unseenCount == null) {
            throw new ItemNotFoundException("user", userId);
        }
        return unseenCount;
    }

    @Override
    public void setAllSeen(String userId) throws ItemNotFoundException {
        List<Notification> unseenNotifications = notificationRepository.findByUserIdAndSeenFalse(userId);
        if (unseenNotifications.isEmpty()) {
            throw new ItemNotFoundException("No unseen notifications for the user", userId);
        }

        for (Notification notification : unseenNotifications) {
            notification.setSeen(true);
        }

        notificationRepository.saveAll(unseenNotifications);
    }

    private void verifyUser(String userId) throws ItemNotFoundException {
        Preference preference = preferenceService.getByUserId(userId);
        if(preference.getEmail() == null || preference.getEmail().isEmpty()){
            String email = emailGetter.get(userId);
            if(email == null) throw new ItemNotFoundException("user", userId);
            preference.setEmail(email);
            preferenceService.update(userId, preference);
        }
    }

}

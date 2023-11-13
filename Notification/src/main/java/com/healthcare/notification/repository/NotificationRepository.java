package com.healthcare.notification.repository;

import com.healthcare.notification.entities.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Page<Notification> findByUserId(String userId, Pageable pageable);

    Integer countByUserIdAndSeenFalse(String userId);

    List<Notification> findByUserIdAndSeenFalse(String userId);
}


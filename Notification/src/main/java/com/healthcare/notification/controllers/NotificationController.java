package com.healthcare.notification.controllers;

import com.healthcare.notification.entities.Notification;
import com.healthcare.notification.exceptions.AccessMismatchException;
import com.healthcare.notification.exceptions.ItemNotFoundException;
import com.healthcare.notification.service.interfaces.NotificationService;
import com.healthcare.notification.utilities.token.IDExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<String> createNotification(@RequestBody Notification notification) {
        notificationService.create(notification);
        return new ResponseEntity<String>("Notification created", HttpStatus.CREATED);
    }

    @GetMapping("/{itemCount}/{pageNo}")
    public ResponseEntity<List<Notification>> getAllNotifications(@PathVariable int itemCount, @PathVariable int pageNo) {
        return ResponseEntity.ok(notificationService.getAllByUserId("PAT1", itemCount, pageNo));
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<String> deleteNotification(@PathVariable Long notificationId) throws ItemNotFoundException, AccessMismatchException {
        notificationService.delete(notificationId, "PAT1");
        return new ResponseEntity<String>("Notification deleted", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/set-seen/{notificationId}")
    public ResponseEntity<String> setSeen(@PathVariable Long notificationId) throws AccessMismatchException, ItemNotFoundException, IllegalAccessException {
        notificationService.setSeen(notificationId, "PAT1");
        return new ResponseEntity<String>("Notification updated to seen status", HttpStatus.OK);
    }
}
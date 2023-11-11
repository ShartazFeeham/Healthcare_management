package com.healthcare.notification.service.interfaces;
import com.healthapp.notificationservice.entities.Preference;
import java.util.UUID;

public interface PreferenceService {
    Preference getByUserId(UUID userId);
    void update(UUID userId, Preference updatedPreference);
    Preference resetToDefault(UUID userId);
}
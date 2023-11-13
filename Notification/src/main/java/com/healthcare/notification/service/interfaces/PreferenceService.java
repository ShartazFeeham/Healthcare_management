package com.healthcare.notification.service.interfaces;
import com.healthcare.notification.entities.Preference;
import com.healthcare.notification.exceptions.ItemNotFoundException;
import com.healthcare.notification.model.DeviceRequest;

import java.util.UUID;

public interface PreferenceService {
    Preference create(String userId, String email);
    Preference getByUserId(String userId);
    void update(String userId, Preference updatedPreference);
    void addDevice(DeviceRequest deviceRequest);
    void removeDevice(DeviceRequest deviceRequest) throws ItemNotFoundException;
}
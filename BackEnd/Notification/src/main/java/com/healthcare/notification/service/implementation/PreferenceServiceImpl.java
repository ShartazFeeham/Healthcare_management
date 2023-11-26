package com.healthcare.notification.service.implementation;

import com.healthcare.notification.entities.Device;
import com.healthcare.notification.entities.Preference;
import com.healthcare.notification.exceptions.ItemNotFoundException;
import com.healthcare.notification.model.DeviceRequest;
import com.healthcare.notification.repository.DeviceRepository;
import com.healthcare.notification.repository.PreferenceRepository;
import com.healthcare.notification.service.interfaces.PreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PreferenceServiceImpl implements PreferenceService {

    private final PreferenceRepository preferenceRepository;
    private final DeviceRepository deviceRepository;

    @Override
    public Preference create(String userId, String email) {
        Preference preference = new Preference();
        preference.setPreferenceId(userId);
        preference.setEmail(email);
        preference.setGetEmailNotifications(true);
        preference.setGetSMSNotifications(true);
        preference.setGetPushNotifications(true);
        preference.setGetAppointmentUpdates(true);
        preference.setGetSiteUpdates(true);
        preference.setGetAccountAccountUpdates(true);
        preference.setGetCommunityUpdates(true);

        preferenceRepository.save(preference);
        return preference;
    }

    @Override
    public Preference getByUserId(String userId) {
        Optional<Preference> preferenceOp = preferenceRepository.findById(userId);
        if (preferenceOp.isEmpty()) {
            Preference preference = create(userId, "");
            preferenceRepository.save(preference);
            return preference;
        } else {
            return preferenceOp.get();
        }
    }

    @Override
    public void update(String userId, Preference updatedPreference) {
        Preference preference = getByUserId(userId);

        preference.setGetEmailNotifications(updatedPreference.isGetEmailNotifications());
        preference.setGetSMSNotifications(updatedPreference.isGetSMSNotifications());
        preference.setGetPushNotifications(updatedPreference.isGetPushNotifications());
        preference.setDoNotDisturb(updatedPreference.isDoNotDisturb());

        preference.setGetCommunityUpdates(updatedPreference.isGetCommunityUpdates());
        preference.setGetAppointmentUpdates(updatedPreference.isGetAppointmentUpdates());
        preference.setGetAppointmentUpdates(updatedPreference.isGetCommunityUpdates());
        preference.setGetSiteUpdates(updatedPreference.isGetSiteUpdates());

        preferenceRepository.save(preference);
    }

    @Override
    public void addDevice(DeviceRequest deviceRequest) {
        Preference preference = getByUserId(deviceRequest.getUserId());
        if(preference.getEmail() == null || preference.getEmail().isEmpty()) {
            preference.setEmail(deviceRequest.getEmail());
        }
        if(preference.getDevices() == null) preference.setDevices(new ArrayList<>());
        if(!preference.getDevices().stream()
                .filter(d -> d.getDeviceCode().equals(deviceRequest.getDeviceCode()))
                .collect(Collectors.toSet()).isEmpty()){
            return;
        }
        preference.getDevices().add(new Device(deviceRequest.getDeviceCode(), preference));
        preferenceRepository.save(preference);
    }

    @Override
    public void removeDevice(DeviceRequest deviceRequest) throws ItemNotFoundException {
        Preference preference = getByUserId(deviceRequest.getUserId());
        if(preference.getDevices() == null) preference.setDevices(new ArrayList<>());
        Long deviceId = 0L;
        for(Device device: preference.getDevices()){
            if(device.getDeviceCode().equals(deviceRequest.getDeviceCode())){
                device.setPreference(null);
                deviceRepository.delete(device);
                return;
            }
        }
        throw new ItemNotFoundException("device",
                deviceRequest.getDeviceCode().substring(0, 5) + "...");
    }

    @Override
    public void updatePhoneNo(String userId, String phoneNo) {
        Preference preference = getByUserId(userId);
        if(!phoneNo.startsWith("+")){
            phoneNo = "+88" + phoneNo;
        }
        preference.setPhoneNo(phoneNo);

        preferenceRepository.save(preference);
    }
}

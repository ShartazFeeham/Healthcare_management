package com.healthcare.notification.service.implementation;

import com.healthcare.notification.entities.Notification;
import com.healthcare.notification.entities.Preference;
import com.healthcare.notification.enums.NotificationType;
import com.healthcare.notification.network.EmailSender;
import com.healthcare.notification.network.PushSender;
import com.healthcare.notification.network.SMSSender;
import com.healthcare.notification.service.interfaces.NotifyService;
import com.healthcare.notification.service.interfaces.PreferenceService;
import com.healthcare.notification.utilities.constants.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class NotifyServiceImpl implements NotifyService {

    private final PreferenceService preferenceService;
    private final EmailSender emailSender;
    private final PushSender pushSender;
    private final SMSSender smsSender;

    @Override
    public void send(Notification notification) {
        Preference preference = preferenceService.getByUserId(notification.getUserId());

        if (filter(notification, preference)) {
            if(preference.isGetPushNotifications()) sendPush(notification, preference);
            if(preference.isGetEmailNotifications()) sendEmail(notification, preference);
            if(preference.isGetSMSNotifications()) sendSMS(notification, preference);
        }
    }

    private void sendSMS(Notification notification, Preference preference) {
        String text = "-\n\n";
        text = text + notification.getTitle().toUpperCase() + "\n\n"
                + notification.getText()
                + notification.getSuffix() + "\nSee more at: "
                + notification.getUrl() + "\n\n"
                + "EA Healthcare - your health, our concern. ♡";
        smsSender.send(preference.getPhoneNo(), text);
    }

    private boolean filter(Notification notification, Preference preference) {

        if (preference.isDoNotDisturb()) {
            return false;
        }

        return switch (NotificationType.valueOf(notification.getType())) {
            case ACCOUNT -> preference.isGetAccountAccountUpdates();
            case APPOINTMENT -> preference.isGetAppointmentUpdates();
            case COMMUNITY -> preference.isGetCommunityUpdates();
            case SITE -> preference.isGetSiteUpdates();
            default -> true;
        };
    }

    private void sendPush(Notification notification, Preference preference) {
        try {
            for (int i = 0; i < preference.getDevices().size(); i++) {
                String deviceCode = preference.getDevices().get(i).getDeviceCode();
                pushSender.send(deviceCode, notification.getTitle(), notification.getPrefix(), notification.getText());
            }
        }
        catch (Exception e){}
    }

    private void sendEmail(Notification notification, Preference preference) {
        String message = (notification.getPrefix() == null ? "" : (notification.getPrefix() + " "))
                + (notification.getText() == null ? "" : (notification.getText() + " "))
                + (notification.getSuffix() == null ? "" : (notification.getSuffix())) + "/n/n"
                + (notification.getUrl() != null ? "Click the link to see details - " + notification.getUrl() : "");
        emailSender.send(preference.getEmail(), notification.getTitle(), message);
    }
}

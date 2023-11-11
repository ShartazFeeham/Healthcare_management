package com.healthcare.notification.service.implementation;

import com.healthcare.notification.entities.Notification;
import com.healthcare.notification.entities.Preference;
import com.healthcare.notification.enums.NotificationType;
import com.healthcare.notification.network.EmailSender;
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
    private final RestTemplate restTemplate;
    private final EmailSender emailSender;

    @Override
    public void send(Notification notification) {
        Preference preference = preferenceService.getByUserId(notification.getUserId());

        if (filter(notification, preference)) {
            sendPush(notification, preference);
            sendEmail(notification, preference);
        }
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
                String pushJson = "{\n" +
                        "    \"to\": \"" + preference.getDevices().get(i) + "\",\n" +
                        "    \"notification\": {\n" +
                        "        \"body\": \"" + notification.getText() + "\",\n" +
                        "        \"title\": \"" + "Healthcare app" + "\",\n" +
                        "        \"subtitle\": \"" + notification.getSuffix() + "\"\n" +
                        "    }\n" +
                        "}";

                // Send push notification
                restTemplate.postForObject(AppConstants.PUSH_URL, pushJson, String.class);
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

package com.healthcare.notification.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter @RequiredArgsConstructor
public class Preference {
    @Id
    @Column(name = "preference_id")
    private String preferenceId;
    private String email;
    private String phoneNo;
    @OneToMany(mappedBy = "preference", cascade = CascadeType.ALL)
    private List<Device> devices;
    // Personalized notification setting properties based on notification type
    private boolean getEmailNotifications;
    private boolean getSMSNotifications;
    private boolean getPushNotifications;
    private boolean doNotDisturb;
    private boolean getAccountAccountUpdates;
    private boolean getAppointmentUpdates;
    private boolean getCommunityUpdates;
    private boolean getSiteUpdates;
}

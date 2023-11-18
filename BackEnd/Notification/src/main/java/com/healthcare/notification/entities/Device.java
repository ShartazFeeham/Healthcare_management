package com.healthcare.notification.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceId;
    private LocalDateTime logTime;
    private String deviceCode;

    @ManyToOne
    @JoinColumn(name = "preference_id")
    @JsonIgnore
    private Preference preference;

    public Device(String deviceCode, Preference preference) {
        this.deviceCode = deviceCode;
        this.logTime = LocalDateTime.now();
        this.preference = preference;
    }
}

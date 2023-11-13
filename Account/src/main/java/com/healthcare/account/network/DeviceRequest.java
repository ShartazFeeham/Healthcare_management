package com.healthcare.account.network;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class DeviceRequest {
    private String userId;
    private String email;
    private String deviceCode;
}
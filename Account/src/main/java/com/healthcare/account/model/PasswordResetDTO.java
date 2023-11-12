package com.healthcare.account.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PasswordResetDTO {
    private String email;
    private Integer otp;
    private String newPassword;
}

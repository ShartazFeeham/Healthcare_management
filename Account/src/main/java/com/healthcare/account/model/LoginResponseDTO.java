package com.healthcare.account.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class LoginResponseDTO {
    private String userId;
    private String email;
    private String bearerToken;
    private String role;
}

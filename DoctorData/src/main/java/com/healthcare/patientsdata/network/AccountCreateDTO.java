package com.healthcare.patientsdata.network;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class AccountCreateDTO {
    private String userId;
    private String email;
    private String password;
}
package com.healthcare.account.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @RequiredArgsConstructor
public class AdminAccountCreateDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}

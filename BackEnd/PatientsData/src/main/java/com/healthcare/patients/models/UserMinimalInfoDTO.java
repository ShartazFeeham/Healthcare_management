package com.healthcare.patients.models;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class UserMinimalInfoDTO {
    private String firstName;
    private String lastName;
    private String photoURL;
}

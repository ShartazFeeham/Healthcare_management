package com.healthcare.account.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserMinimalInfoDTO {
    private String firstName;
    private String lastName;
    private String photoURL;
}
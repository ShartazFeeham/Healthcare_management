package com.healthcare.account.model;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class ReadForListDTO {
    private String userId;
    private String email;
    private LocalDate registerDate;
    private boolean deactivation;
}

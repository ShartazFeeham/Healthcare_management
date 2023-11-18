package com.healthcare.account.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Entity
public class Account {

    @Id
    private String userId;

    // User info
    private String email;
    private String password;
    @Enumerated
    private Role role;

    // Account info
    private LocalDate registerDate;
    private Integer otp;
    private LocalDateTime otpGenerationTime;
    @Column(columnDefinition = "boolean default false")
    private boolean accountLocked;
    @Column(columnDefinition = "boolean default true")
    private boolean accountEnabled;
    @Column(columnDefinition = "boolean default false")
    private boolean twoFactorEnabled;
    @Column(columnDefinition = "boolean default false")
    private boolean accountDeactivated;
    @Column(columnDefinition = "boolean default false")
    private boolean accountSuspended;
    @Column(columnDefinition = "int default 0")
    private int banCount;
    private LocalDateTime unbanTime;
}

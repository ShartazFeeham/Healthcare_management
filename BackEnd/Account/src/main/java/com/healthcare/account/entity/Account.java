package com.healthcare.account.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Validated
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Entity
public class Account {

    @Id
    private String userId;

    // User info
    @Email(message = "Please provide a valid email address")
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters long")
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

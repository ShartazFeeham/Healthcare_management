package com.healthcare.account.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Entity
public class AdminAccount {

    // Unique identifier for the admin account.
    @Id
    private String userId;

    // First name of the admin. Should be at least 2 characters long.
    @Size(min = 2, message = "First name must be at least 2 characters long")
    private String firstName;

    // Last name of the admin. Should be at least 2 characters long.
    @Size(min = 2, message = "Last name must be at least 2 characters long")
    private String lastName;

}

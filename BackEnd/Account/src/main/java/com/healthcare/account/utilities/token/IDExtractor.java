package com.healthcare.account.utilities.token;

import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

public class IDExtractor {
    public static String getUserID() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }
}

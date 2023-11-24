package com.healthcare.cdss.utilities.token;

import org.springframework.security.core.context.SecurityContextHolder;

public class IDExtractor {
    /**
     * Retrieves the user's UUID from the currently authenticated principal.
     *
     * @return The UUID of the authenticated user.
     */
    public static String getUserID() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }
}
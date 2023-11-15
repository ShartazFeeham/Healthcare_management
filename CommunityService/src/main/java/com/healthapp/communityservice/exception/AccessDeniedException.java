package com.healthapp.communityservice.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class AccessDeniedException extends CustomException {
    public AccessDeniedException(String message) {
        super("AccessDeniedException", "User has no access for - " + message, HttpStatus.FORBIDDEN);
    }
}
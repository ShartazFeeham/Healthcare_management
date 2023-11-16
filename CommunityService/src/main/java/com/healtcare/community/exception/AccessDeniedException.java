package com.healtcare.community.exception;

import com.healtcare.community.exception.CustomException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class AccessDeniedException extends CustomException {
    public AccessDeniedException(String message) {
        super("AccessDeniedException", "Access denied! No access for - " + message, HttpStatus.FORBIDDEN);
    }
}
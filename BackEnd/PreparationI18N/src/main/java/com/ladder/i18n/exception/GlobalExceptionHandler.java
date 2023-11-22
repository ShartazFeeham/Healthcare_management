package com.ladder.i18n.exception;

import com.ladder.i18n.model.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customCommonException(CustomException exception, HttpServletRequest request) {
        return generateResponse(exception, request);
    }

    private ResponseEntity<?> generateResponse(CustomException exception, HttpServletRequest request){
        ErrorResponse response = new ErrorResponse(
                exception.getExceptionName(), exception.getOperation(), exception.getType(),
                        exception.getMessage(), exception.getHttpStatus().toString(), new Date(), request.getRequestURI()
        );
        return new ResponseEntity<>(response, exception.getHttpStatus());
    }
}

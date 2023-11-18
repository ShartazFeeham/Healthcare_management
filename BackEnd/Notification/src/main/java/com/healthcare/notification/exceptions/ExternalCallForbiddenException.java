package com.healthcare.notification.exceptions;

public class ExternalCallForbiddenException extends RuntimeException{
    public ExternalCallForbiddenException(){
        super("Secret token is invalid, external call prohibited!");
    }
}

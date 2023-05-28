package com.chat.app.rest.Exception;

public class UserUnauthorizedException extends RuntimeException {
    public UserUnauthorizedException() {
        super("Unauthorized");
    }
}

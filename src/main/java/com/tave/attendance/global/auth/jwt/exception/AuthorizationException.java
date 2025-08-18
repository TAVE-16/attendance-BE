package com.tave.attendance.global.auth.jwt.exception;

import org.springframework.http.HttpStatus;

import static com.tave.attendance.global.auth.jwt.exception.AuthErrorMessage.UN_AUTHORIZED;

public class AuthorizationException extends AuthException {
    public AuthorizationException() {
        super(UN_AUTHORIZED.getStatus(), UN_AUTHORIZED.getMessage());
    }
}

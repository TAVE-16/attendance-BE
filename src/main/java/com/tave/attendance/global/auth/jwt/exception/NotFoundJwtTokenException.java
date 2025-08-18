package com.tave.attendance.global.auth.jwt.exception;

import static com.tave.attendance.global.auth.jwt.exception.AuthErrorMessage.NOT_FOUND_JWT_TOKEN;

public class NotFoundJwtTokenException extends AuthException {
    public NotFoundJwtTokenException() {
        super(NOT_FOUND_JWT_TOKEN.getStatus(), NOT_FOUND_JWT_TOKEN.getMessage());
    }
}

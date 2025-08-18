package com.tave.attendance.global.auth.jwt.exception;

import static com.tave.attendance.global.auth.jwt.exception.AuthErrorMessage.UN_SUPPORTED_JWT;

public class CustomUnsupportedJwtException extends AuthException {
    public CustomUnsupportedJwtException() {
        super(UN_SUPPORTED_JWT.getStatus(), UN_SUPPORTED_JWT.getMessage());
    }
}

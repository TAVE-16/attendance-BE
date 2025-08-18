package com.tave.attendance.global.auth.jwt.exception;

import static com.tave.attendance.global.auth.jwt.exception.AuthErrorMessage.EXPIRED_JWT;

public class CustomExpiredJwtException extends AuthException {
    public CustomExpiredJwtException() {
        super(EXPIRED_JWT.getStatus(), EXPIRED_JWT.getMessage());
    }
}

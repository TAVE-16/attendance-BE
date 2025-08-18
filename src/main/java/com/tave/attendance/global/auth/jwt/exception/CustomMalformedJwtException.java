package com.tave.attendance.global.auth.jwt.exception;

import static com.tave.attendance.global.auth.jwt.exception.AuthErrorMessage.MALFORMED_JWT;

public class CustomMalformedJwtException extends AuthException {
    public CustomMalformedJwtException() {
        super(MALFORMED_JWT.getStatus(), MALFORMED_JWT.getMessage());
    }
}

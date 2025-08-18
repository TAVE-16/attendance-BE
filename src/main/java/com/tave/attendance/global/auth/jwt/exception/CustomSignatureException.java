package com.tave.attendance.global.auth.jwt.exception;

import static com.tave.attendance.global.auth.jwt.exception.AuthErrorMessage.SIGNATURE_JWT;

public class CustomSignatureException extends AuthException {
    public CustomSignatureException() {
        super(SIGNATURE_JWT.getStatus(), SIGNATURE_JWT.getMessage());
    }
}

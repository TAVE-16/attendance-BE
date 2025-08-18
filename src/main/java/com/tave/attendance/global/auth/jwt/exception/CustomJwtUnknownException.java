package com.tave.attendance.global.auth.jwt.exception;

import static com.tave.attendance.global.auth.jwt.exception.AuthErrorMessage.CAUSE_UNKNOWN;

public class CustomJwtUnknownException extends AuthException {
    public CustomJwtUnknownException() {
        super(CAUSE_UNKNOWN.getStatus(), CAUSE_UNKNOWN.getMessage());
    }
}

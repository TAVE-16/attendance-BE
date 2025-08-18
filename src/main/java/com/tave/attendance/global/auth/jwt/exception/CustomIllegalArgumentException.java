package com.tave.attendance.global.auth.jwt.exception;

import static com.tave.attendance.global.auth.jwt.exception.AuthErrorMessage.ILLEGAL_ARGUMENT;

public class CustomIllegalArgumentException extends AuthException {
    public CustomIllegalArgumentException() {
        super(ILLEGAL_ARGUMENT.getStatus(), ILLEGAL_ARGUMENT.getMessage());
    }
}

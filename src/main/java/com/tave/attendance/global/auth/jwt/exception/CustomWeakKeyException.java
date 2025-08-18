package com.tave.attendance.global.auth.jwt.exception;

import static com.tave.attendance.global.auth.jwt.exception.AuthErrorMessage.WEAK_KEY;

public class CustomWeakKeyException extends AuthException {
    public CustomWeakKeyException() {
        super(WEAK_KEY.getStatus(), WEAK_KEY.getMessage());
    }
}

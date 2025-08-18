package com.tave.attendance.global.auth.jwt.exception;

import com.tave.attendance.global.common.exception.BaseException;
import org.springframework.http.HttpStatus;

public class AuthException extends BaseException {

    public AuthException(HttpStatus status, String message) {
        super(status, message);
    }

}

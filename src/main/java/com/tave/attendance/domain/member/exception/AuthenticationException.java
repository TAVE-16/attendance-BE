package com.tave.attendance.domain.member.exception;

import com.tave.attendance.global.common.exception.BaseException;

import static com.tave.attendance.domain.member.exception.ErrorMessage.AUTHENTICATION_FAILED;

public class AuthenticationException extends BaseException {
    public AuthenticationException() {
        super(AUTHENTICATION_FAILED.getStatus(), AUTHENTICATION_FAILED.getMessage());
    }
}

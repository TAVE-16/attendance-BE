package com.tave.attendance.domain.session.exception;

import com.tave.attendance.global.common.exception.BaseException;

import static com.tave.attendance.domain.session.exception.ErrorMessage.SESSION_NOT_FOUND;

public class SessionNotFoundException extends BaseException {
    public SessionNotFoundException() {
        super(SESSION_NOT_FOUND.getStatus(), SESSION_NOT_FOUND.getMessage());
    }
}

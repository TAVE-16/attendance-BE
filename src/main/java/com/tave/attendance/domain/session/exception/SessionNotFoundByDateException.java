package com.tave.attendance.domain.session.exception;

import com.tave.attendance.global.common.exception.BaseException;

import static com.tave.attendance.domain.session.exception.ErrorMessage.SESSION_NOT_FOUND_BY_DATE;

public class SessionNotFoundByDateException extends BaseException {
    public SessionNotFoundByDateException() {
        super(SESSION_NOT_FOUND_BY_DATE.getStatus(), SESSION_NOT_FOUND_BY_DATE.getMessage());
    }
}
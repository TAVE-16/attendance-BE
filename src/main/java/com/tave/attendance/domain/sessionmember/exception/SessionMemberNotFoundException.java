package com.tave.attendance.domain.sessionmember.exception;

import com.tave.attendance.global.common.exception.BaseException;

import static com.tave.attendance.domain.member.exception.ErrorMessage.PHONENUMBER_MISMATCH;

public class SessionMemberNotFoundException extends BaseException {
    public SessionMemberNotFoundException() {
        super(PHONENUMBER_MISMATCH.getStatus(), PHONENUMBER_MISMATCH.getMessage());
    }
}

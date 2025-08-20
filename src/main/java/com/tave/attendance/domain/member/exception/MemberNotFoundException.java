package com.tave.attendance.domain.member.exception;

import com.tave.attendance.global.common.exception.BaseException;

import static com.tave.attendance.domain.member.exception.ErrorMessage.MEMBER_NOT_FOUND;

public class MemberNotFoundException extends BaseException {
    public MemberNotFoundException() {
        super(MEMBER_NOT_FOUND.getStatus(), MEMBER_NOT_FOUND.getMessage());
    }
}

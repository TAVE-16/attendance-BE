package com.tave.attendance.domain.member.exception;

import com.tave.attendance.global.common.exception.BaseException;

import static com.tave.attendance.domain.member.exception.ErrorMessage.INVALID_PHONE_LAST8;

public class InvalidPhoneLast8Exception extends BaseException {
    public InvalidPhoneLast8Exception() {
        super(INVALID_PHONE_LAST8.getStatus(), INVALID_PHONE_LAST8.getMessage());
    }
}

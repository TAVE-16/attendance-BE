package com.tave.attendance.domain.member.exception;

import com.tave.attendance.global.common.exception.BaseException;

import static com.tave.attendance.domain.member.exception.ErrorMessage.PHONENUMBER_MISMATCH;

public class PhonenumberMismatchException extends BaseException {
    public PhonenumberMismatchException() {
        super(PHONENUMBER_MISMATCH.getStatus(), PHONENUMBER_MISMATCH.getMessage());
    }
}

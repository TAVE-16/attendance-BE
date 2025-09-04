package com.tave.attendance.domain.member.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

    // Auth
    AUTHENTICATION_FAILED(UNAUTHORIZED, "[로그인]에 실패했습니다."),

    // Member
    MEMBER_NOT_FOUND(BAD_REQUEST, "존재하지 않는 [회원]입니다."),

    PHONENUMBER_MISMATCH(BAD_REQUEST, "전화번호가 일치하는 [회원]이 없습니다."),

    INVALID_PHONE_LAST8(BAD_REQUEST, "전화번호 끝 8자리를 정확히 입력하세요.");

    private final HttpStatus status;
    private final String message;

}

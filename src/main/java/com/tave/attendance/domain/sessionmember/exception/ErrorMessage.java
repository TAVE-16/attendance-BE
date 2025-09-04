package com.tave.attendance.domain.sessionmember.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

    SESSION_MEMBER_NOT_FOUND(NOT_FOUND, "[세션] [멤버]를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;
}

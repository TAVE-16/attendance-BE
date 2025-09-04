package com.tave.attendance.domain.session.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

    SESSION_NOT_FOUND(BAD_REQUEST, "존재하지 않는 [세션]입니다."),

    SESSION_NOT_FOUND_BY_DATE(BAD_REQUEST, "해당 날짜에 해당하는 [세션]이 존재하지 않습니다.");

    private final HttpStatus status;
    private final String message;

}

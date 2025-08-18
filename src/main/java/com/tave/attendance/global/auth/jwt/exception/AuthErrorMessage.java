package com.tave.attendance.global.auth.jwt.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum AuthErrorMessage {

    UN_AUTHORIZED(FORBIDDEN,"잘못된 권한 요청입니다."),
    NOT_EXIST_TOKEN(UNAUTHORIZED, "JWT 토큰이 존재하지 않습니다."),
    EXPIRED_JWT(UNAUTHORIZED, "JWT 토큰이 만료되었습니다."),
    UN_SUPPORTED_JWT(UNAUTHORIZED, "지원되지 않는 JWT 토큰입니다."),
    MALFORMED_JWT(UNAUTHORIZED, "잘못된 형식의 JWT 토큰입니다."),
    SIGNATURE_JWT(UNAUTHORIZED, "JWT 서명 검증에 실패했습니다."),
    ILLEGAL_ARGUMENT(UNAUTHORIZED, "잘못된 JWT 토큰입니다."),
    WEAK_KEY(UNAUTHORIZED, "JWT 서명 KEY가 너무 약합니다."),
    CAUSE_UNKNOWN(UNAUTHORIZED, "원인 미상의 오류가 발생했습니다."),

    NOT_FOUND_JWT_TOKEN(UNAUTHORIZED, "요청 헤더로부터 JWT 토큰을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;

}

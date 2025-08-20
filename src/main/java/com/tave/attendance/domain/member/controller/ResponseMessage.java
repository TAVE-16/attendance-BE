package com.tave.attendance.domain.member.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {
    // Auth
    ADMIN_LOGIN_SUCCESS("[관리자] 로그인에 성공했습니다."),

    // Admin
    ADMIN_REGISTER_SUCCESS("[관리자] 회원가입에 성공했습니다.");

    private final String message;

}

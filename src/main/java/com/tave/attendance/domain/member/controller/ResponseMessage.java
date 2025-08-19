package com.tave.attendance.domain.member.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {

    // Admin
    ADMIN_REGISTER_SUCCESS("[관리자] 회원가입에 성공했습니다.");

    private final String message;

}

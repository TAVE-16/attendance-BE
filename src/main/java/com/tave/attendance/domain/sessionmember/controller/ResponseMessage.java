package com.tave.attendance.domain.sessionmember.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {
    SESSION_MEMBER_GET_SUCCESS("[세션 멤버] 세션 멤버 조회에 성공했습니다.");

    private final String message;

}

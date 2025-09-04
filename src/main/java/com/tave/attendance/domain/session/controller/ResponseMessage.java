package com.tave.attendance.domain.session.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {

    SESSION_CREATE_SUCCESS("[세션] 생성에 성공했습니다."),

    SESSION_GET_ALL_SUCCESS("[세션] 전체 조회에 성공했습니다."),

    SESSION_GET_SUCCESS("[세션] 단일 조회에 성공했습니다."),

    SESSION_UPDATE_SUCCESS("[세션] 수정에 성공했습니다."),

    SESSION_DELETE_SUCCESS("[세션] 삭제에 성공했습니다."),

    ATTENDANCE_MARK_SUCCESS("[출석] 출석 체크에 성공했습니다.");

    private final String message;

}

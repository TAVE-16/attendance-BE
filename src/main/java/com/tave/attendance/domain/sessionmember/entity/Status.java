package com.tave.attendance.domain.sessionmember.entity;

public enum Status {
    ABSENT,        // 기본값(결석)
    EARLY_BIRD,    // 얼리버드
    ATTENDANCE,       // 정상 출석
    TARDY          // 지각
}
package com.tave.attendance.domain.member.utils;

import com.tave.attendance.domain.sessionmember.entity.Status;

import java.time.LocalDateTime;

public final class ScoreUtils {
    private ScoreUtils() {}

    /** 단순 점수 매핑: 얼리버드 +5, 출석 0, 지각은 10분당 -10(최대 -30), 결석 -30 */
    public static int scoreOf(Status status, LocalDateTime attendanceTime, LocalDateTime tardyStart) {
        return switch (status) {
            case EARLY_BIRD -> 5;
            case ATTENDANCE -> 0;
            case TARDY -> tardyPenalty(attendanceTime, tardyStart);
            case ABSENT -> -30;
        };
    }

    /** 지각 점수: 10분당 -10, 최대 -30 (음수만 반환) */
    private static int tardyPenalty(LocalDateTime attend, LocalDateTime tardyStart) {
        if (attend == null || !attend.isAfter(tardyStart)) return 0;
        long minutesLate = java.time.Duration.between(tardyStart, attend).toMinutes();
        long blocks = (minutesLate + 9) / 10; // 올림 처리
        long penalty = Math.min(3, blocks) * 10; // 최대 30
        return (int) -penalty;
    }
}
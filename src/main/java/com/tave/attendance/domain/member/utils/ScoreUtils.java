package com.tave.attendance.domain.member.utils;

import com.tave.attendance.domain.sessionmember.entity.Status;

import java.time.LocalDateTime;

public final class ScoreUtils {
    private ScoreUtils() {}

    public static int penaltyOf(Status status, LocalDateTime checkedAt, LocalDateTime tardyStart) {
        if (status == null) return 0;

        switch (status) {
            case EARLY_BIRD, ATTENDANCE -> {
                return 0;
            }
            case TARDY -> {
                if (checkedAt == null || tardyStart == null) return 0;
                long minutesLate = Math.max(0, java.time.Duration.between(tardyStart, checkedAt).toMinutes());
                int buckets = (int) Math.ceil(minutesLate / 10.0); // 0–10 → 1, 11–20 → 2, 21+ → 3...
                return -10 * Math.min(buckets, 3); // 상한 -30
            }
            case ABSENT -> {
                return -30;
            }
            default -> {
                return 0;
            }
        }
    }
}
package com.tave.attendance.domain.session.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record SessionDto(
        Long id,
        String title,
        LocalDate sessionDate,
        LocalTime tardyTime,
        LocalTime earlyBirdDeadline,
        LocalTime seminarTime
) {}

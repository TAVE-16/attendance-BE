package com.tave.attendance.domain.sessionmember.dto;

public record MarkAttendanceReqDto(
        @jakarta.validation.constraints.NotBlank
        @jakarta.validation.constraints.Pattern(regexp="^\\d{8}$", message="전화번호 끝 8자리만 입력하세요.")
        String phoneNumber
) {}
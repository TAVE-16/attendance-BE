package com.tave.attendance.domain.sessionmember.dto;

import com.tave.attendance.domain.member.entity.FieldType;
import com.tave.attendance.domain.sessionmember.entity.Status;

public record SessionMemberResDto(
        Long memberId,
        String username,
        FieldType field,
        Status status
) {}

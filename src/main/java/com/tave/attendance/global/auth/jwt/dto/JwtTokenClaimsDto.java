package com.tave.attendance.global.auth.jwt.dto;

import com.tave.attendance.domain.member.entity.Role;
import lombok.Builder;

@Builder
public record JwtTokenClaimsDto(
        Long memberId,
        Role role
){
    public static JwtTokenClaimsDto of(final Long memberId, final String role) {
        return JwtTokenClaimsDto.builder()
                .memberId(memberId)
                .role(Role.valueOf(role))
                .build();
    }
}

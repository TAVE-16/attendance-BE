package com.tave.attendance.global.auth.jwt.dto;

import lombok.Builder;

@Builder
public record JwtTokenResponseDto(
        String accessToken
) {
    public static JwtTokenResponseDto of(final String accessToken) {
        return JwtTokenResponseDto.builder()
                .accessToken(accessToken)
                .build();
    }
}

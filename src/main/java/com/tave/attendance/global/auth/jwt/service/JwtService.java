package com.tave.attendance.global.auth.jwt.service;

import com.tave.attendance.domain.member.Role;
import com.tave.attendance.global.auth.jwt.JwtTokenType;
import com.tave.attendance.global.auth.jwt.dto.JwtTokenClaimsDto;
import com.tave.attendance.global.auth.jwt.dto.JwtTokenResponseDto;
import com.tave.attendance.global.auth.jwt.exception.NotFoundJwtTokenException;
import com.tave.attendance.global.auth.jwt.utils.JwtExtractor;
import com.tave.attendance.global.auth.jwt.utils.JwtProvider;
import com.tave.attendance.global.auth.jwt.utils.JwtValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProvider jwtProvider;
    private final JwtValidator jwtValidator;
    private final JwtExtractor jwtExtractor;

    public JwtTokenResponseDto generateJwtToken(Long memberId, Role role) {
        String accessToken = jwtProvider.generateToken(JwtTokenType.ACCESS_TOKEN, memberId, role.name());

        return JwtTokenResponseDto.of(accessToken);
    }

    public String extractJwtToken(HttpServletRequest request) {
        return jwtExtractor.extractJwtToken(request)
                .orElseThrow(NotFoundJwtTokenException::new);
    }

    // 유효하지 않은 경우 예외 처리 -> void 반환.
    public void isValidToken(String token) {
        jwtValidator.verifyAccessToken(token);
    }

    public JwtTokenClaimsDto extractJwtToken(String token) {
        Long id = jwtExtractor.getId(token);
        String role = jwtExtractor.getRole(token);
        return JwtTokenClaimsDto.of(id, role);
    }

}

package com.tave.attendance.global.auth.jwt.utils;

import com.tave.attendance.global.auth.jwt.JwtTokenType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import static com.tave.attendance.global.auth.constants.AuthConstant.*;

@Component
public class JwtProvider {

    private final Key key;

    public JwtProvider(@Value("${tave.key}") String secretKey) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    @Value("${tave.accessTokenExpiration}")
    private Long accessTokenExpiration;

    public String generateToken(JwtTokenType jwtTokenType, Long memberId, String role) {
        return Jwts.builder()
                .claim(ID_CLAIM, memberId)
                .claim(ROLE_CLAIM, role)
                .subject(String.valueOf(memberId))
                .issuedAt(new Date())
                .expiration(setExpireTime((jwtTokenType)))
                .signWith(key)
                .compact();
    }

    /*
    * 여러 Token이 추가 될 가능성을 고려 -> JwtTokenType별로 만료 기간 분리
    * */

    private Date setExpireTime(JwtTokenType jwtTokenType) {
        return switch (jwtTokenType) {
            case ACCESS_TOKEN -> new Date(System.currentTimeMillis() + accessTokenExpiration);
        };
    }

}

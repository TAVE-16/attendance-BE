package com.tave.attendance.global.auth.jwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Optional;

import static com.tave.attendance.global.auth.constants.AuthConstant.*;

@Component
public class JwtExtractor {

    private final Key key;

    public JwtExtractor(@Value("${tave.key}") String secretKey) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public Optional<String> extractJwtToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(ACCESS_TOKEN_HEADER))
                .filter(refreshToken -> refreshToken.startsWith(AUTH_PREFIX))
                .map(refreshToken -> refreshToken.replace(AUTH_PREFIX, ""));
    }

    public Long getId(String token){
        return getMemberIdClaim(token, ID_CLAIM);
    }

    public String getRole(String token) {
        return getStringClaim(token, ROLE_CLAIM);
    }

    private Long getMemberIdClaim(String token, String claimName) {
        Claims claims = parseClaims(token);
        return claims.get(claimName, Long.class);
    }

    private String getStringClaim(String token, String claimName) {
        Claims claims = parseClaims(token);
        return claims.get(claimName, String.class);
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}

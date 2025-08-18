package com.tave.attendance.global.auth.jwt.utils;

import com.tave.attendance.global.auth.jwt.exception.*;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;

@Component
public class JwtValidator {

    private final Key key;

    public JwtValidator(@Value("${tave.key}") String secretKey) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public void verifyAccessToken(String token) {
        try{
            Jwts.parser()
                    .verifyWith((SecretKey) key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        }catch (ExpiredJwtException e) {
            throw new CustomExpiredJwtException();
        } catch (UnsupportedJwtException e) {
            throw new CustomUnsupportedJwtException();
        } catch (MalformedJwtException e) {
            throw new CustomMalformedJwtException();
        } catch (SignatureException e) {
            throw new CustomSignatureException();
        } catch (IllegalArgumentException e) {
            throw new CustomIllegalArgumentException();
        } catch (WeakKeyException e) {
            throw new CustomWeakKeyException();
        } catch (Exception e) {
            throw new CustomJwtUnknownException();
        }
    }

}

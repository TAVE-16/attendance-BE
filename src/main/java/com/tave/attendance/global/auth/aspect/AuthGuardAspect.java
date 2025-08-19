package com.tave.attendance.global.auth.aspect;

import com.tave.attendance.domain.member.entity.Role;
import com.tave.attendance.global.auth.annotations.AuthGuard;
import com.tave.attendance.global.auth.context.MemberContext;
import com.tave.attendance.global.auth.guards.Authorizable;
import com.tave.attendance.global.auth.jwt.dto.JwtTokenClaimsDto;
import com.tave.attendance.global.auth.jwt.exception.AuthorizationException;
import com.tave.attendance.global.auth.jwt.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthGuardAspect {

    private final ApplicationContext applicationContext;
    private final JwtService jwtService;

    @Around("@annotation(authGuard)")
    public Object applyGuards(ProceedingJoinPoint joinPoint, AuthGuard authGuard) throws Throwable {
        // 토큰 추출
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = jwtService.extractJwtToken(httpServletRequest);
        jwtService.isValidToken(token);
        JwtTokenClaimsDto memberClaims = jwtService.extractJwtToken(token);


        // 인가 검증 후 Context 저장
        authorize(authGuard.value(), memberClaims.role());
        MemberContext.setCurrentMemberClaims(memberClaims);

        // 본문 실행
        Object proceed = joinPoint.proceed();

        // Context Clear
        MemberContext.clear();

        return proceed;
    }

    public void authorize(Class<? extends Authorizable>[] authGuards, Role tokenRole) {
        boolean isAuthorized = Arrays.stream(authGuards)
                .map(applicationContext::getBean)
                .anyMatch(guard -> guard.isAuthorized(tokenRole));

        if (!isAuthorized) {
            throw new AuthorizationException();
        }
    }

}

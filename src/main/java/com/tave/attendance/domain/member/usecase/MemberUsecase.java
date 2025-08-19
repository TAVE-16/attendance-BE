package com.tave.attendance.domain.member.usecase;

import com.tave.attendance.domain.member.entity.Member;
import com.tave.attendance.domain.member.exception.AuthenticationException;
import com.tave.attendance.domain.member.service.AuthService;
import com.tave.attendance.domain.member.service.MemberGetService;
import com.tave.attendance.domain.member.service.MemberSaveService;
import com.tave.attendance.global.auth.jwt.dto.JwtTokenResponseDto;
import com.tave.attendance.global.auth.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.tave.attendance.domain.member.dto.AdminDto.*;


@Service
@RequiredArgsConstructor
public class MemberUsecase {

    private final MemberSaveService memberSaveService;

    public void registerAdmin(AuthInfo registerDto) {
        memberSaveService.saveAdmin(registerDto);
    }

}

package com.tave.attendance.domain.member.usecase;

import com.tave.attendance.domain.member.entity.Member;
import com.tave.attendance.domain.member.service.AuthService;
import com.tave.attendance.domain.member.service.MemberGetService;
import com.tave.attendance.domain.member.service.MemberSaveService;
import com.tave.attendance.global.auth.jwt.dto.JwtTokenResponseDto;
import com.tave.attendance.global.auth.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.tave.attendance.domain.member.dto.AdminDto.*;


@Service
@RequiredArgsConstructor
public class MemberUsecase {

    private final MemberSaveService memberSaveService;
    private final MemberGetService memberGetService;
    private final AuthService authService;
    private final JwtService jwtService;

    public void registerAdmin(AuthInfo registerDto) {
        memberSaveService.saveAdmin(registerDto);
    }

    public JwtTokenResponseDto loginAdmin(AuthInfo loginDto) {
        // 인증 절차
        Member findAdmin = memberGetService.findMember(loginDto.email());
        authService.checkPassword(loginDto.password(), findAdmin.getPassword());

        // 토큰 발급
        return jwtService.generateJwtToken(findAdmin.getId(), findAdmin.getRole());
    }

}

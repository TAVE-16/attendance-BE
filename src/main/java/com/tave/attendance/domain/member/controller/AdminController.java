package com.tave.attendance.domain.member.controller;

import com.tave.attendance.domain.member.usecase.MemberUsecase;
import com.tave.attendance.global.auth.jwt.dto.JwtTokenResponseDto;
import com.tave.attendance.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.tave.attendance.domain.member.controller.ResponseMessage.*;
import static com.tave.attendance.domain.member.dto.AdminDto.*;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final MemberUsecase memberUsecase;

    @PostMapping("/v1/admin/signup")
    public ApiResponse<Void> registerAdmin(@RequestBody AuthInfo dto) {
        memberUsecase.registerAdmin(dto);
        return ApiResponse.response(HttpStatus.OK, ADMIN_REGISTER_SUCCESS.getMessage());
    }

    @PostMapping("/v1/admin/signin")
    public ApiResponse<JwtTokenResponseDto> loginAdmin(@RequestBody AuthInfo dto) {
        JwtTokenResponseDto response = memberUsecase.authenticateAdmin(dto);
        return ApiResponse.response(HttpStatus.OK, ADMIN_LOGIN_SUCCESS.getMessage(), response);
    }

}

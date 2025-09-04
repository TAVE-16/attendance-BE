package com.tave.attendance.domain.sessionmember.controller;

import com.tave.attendance.domain.member.entity.FieldType;
import com.tave.attendance.domain.session.service.AttendanceService;
import com.tave.attendance.domain.sessionmember.dto.SessionMemberResDto;
import com.tave.attendance.domain.sessionmember.entity.Status;
import com.tave.attendance.domain.sessionmember.service.SessionMemberService;
import com.tave.attendance.global.auth.annotations.AuthGuard;
import com.tave.attendance.global.auth.guards.AdminGuard;
import com.tave.attendance.global.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tave.attendance.domain.sessionmember.controller.ResponseMessage.SESSION_MEMBER_GET_SUCCESS;

@RestController
@RequiredArgsConstructor
public class SessionMemberController {

    private final SessionMemberService sessionMemberService;

    @GetMapping("/v1/sessions/{sessionId}/members")
    @Operation(summary = "[SESSION MEMBER] 세션 멤버 조회 API")
    @AuthGuard({AdminGuard.class})
    public ApiResponse<List<SessionMemberResDto>> getMembers(
            @PathVariable Long sessionId,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false, name = "field") FieldType field
    ) {
        return ApiResponse.response(
                HttpStatus.OK, SESSION_MEMBER_GET_SUCCESS.getMessage(), sessionMemberService.getMembers(sessionId, status, field));
    }
}

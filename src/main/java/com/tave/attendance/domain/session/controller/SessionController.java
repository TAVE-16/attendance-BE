package com.tave.attendance.domain.session.controller;

import com.tave.attendance.domain.session.dto.SessionCreateReqDto;
import com.tave.attendance.domain.session.dto.SessionDto;
import com.tave.attendance.domain.session.dto.SessionUpdateReqDto;
import com.tave.attendance.domain.session.service.SessionService;
import com.tave.attendance.global.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tave.attendance.domain.session.controller.ResponseMessage.*;


@RestController
@RequestMapping("/v1/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @PostMapping
    @Operation(summary = "[SESSION] 세션 생성 API")
    public ApiResponse<SessionDto> create(@RequestBody SessionCreateReqDto request) {
        SessionDto response = sessionService.create(request);
        return ApiResponse.response(HttpStatus.OK, SESSION_CREATE_SUCCESS.getMessage(), response);
    }

    @GetMapping
    @Operation(summary = "[SESSION] 전체 세션 조회 API")
    public ApiResponse<List<SessionDto>> getAll() {
        return ApiResponse.response(HttpStatus.OK, SESSION_GET_ALL_SUCCESS.getMessage(), sessionService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "[SESSION] 단일 세션 조회 API")
    public ApiResponse<SessionDto> getById(@PathVariable Long id) {
        return ApiResponse.response(HttpStatus.OK, SESSION_GET_SUCCESS.getMessage(), sessionService.getById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "[SESSION] 세션 수정 API")
    public ApiResponse<Void> update(@PathVariable Long id, @RequestBody SessionUpdateReqDto request) {
        sessionService.update(id, request);
        return ApiResponse.response(HttpStatus.OK, SESSION_UPDATE_SUCCESS.getMessage());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "[SESSION] 세션 삭제 API")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        sessionService.delete(id);
        return ApiResponse.response(HttpStatus.OK, SESSION_DELETE_SUCCESS.getMessage());
    }
}
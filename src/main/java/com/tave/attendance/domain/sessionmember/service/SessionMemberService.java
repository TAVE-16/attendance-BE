package com.tave.attendance.domain.sessionmember.service;

import com.tave.attendance.domain.member.entity.FieldType;
import com.tave.attendance.domain.sessionmember.dto.SessionMemberResDto;
import com.tave.attendance.domain.sessionmember.entity.Status;
import com.tave.attendance.domain.sessionmember.repository.SessionMemberRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionMemberService {

    private final SessionMemberRepository sessionMemberRepository;

    public List<SessionMemberResDto> getMembers(
            Long sessionId, @Nullable Status status, @Nullable FieldType field
    ) {
        return sessionMemberRepository.findMembersOfSession(sessionId, status, field);
    }
}

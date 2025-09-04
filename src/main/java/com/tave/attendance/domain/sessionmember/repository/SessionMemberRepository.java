package com.tave.attendance.domain.sessionmember.repository;

import com.tave.attendance.domain.sessionmember.entity.SessionMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionMemberRepository extends JpaRepository<SessionMember, Long> {
    Optional<SessionMember> findBySession_IdAndMember_Id(Long sessionId, Long memberId);
}
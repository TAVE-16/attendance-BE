package com.tave.attendance.domain.sessionmember.repository;

import com.tave.attendance.domain.member.entity.FieldType;
import com.tave.attendance.domain.sessionmember.dto.SessionMemberResDto;
import com.tave.attendance.domain.sessionmember.entity.SessionMember;
import com.tave.attendance.domain.sessionmember.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SessionMemberRepository extends JpaRepository<SessionMember, Long> {
    Optional<SessionMember> findBySessionIdAndMemberId(Long sessionId, Long memberId);

    @Query("""
        select new com.tave.attendance.domain.sessionmember.dto.SessionMemberResDto(
            m.id, m.username, m.field, sm.status
        )
        from SessionMember sm
        join sm.member m
        where sm.session.id = :sessionId
          and (:status is null or sm.status = :status)
          and (:field  is null or m.field   = :field)
        order by m.username asc
    """)
    List<SessionMemberResDto> findMembersOfSession(
            @Param("sessionId") Long sessionId,
            @Param("status") Status status,
            @Param("field")  FieldType field
    );
}
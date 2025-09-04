package com.tave.attendance.domain.session.service;

import com.tave.attendance.domain.member.entity.Member;
import com.tave.attendance.domain.member.exception.PhonenumberMismatchException;
import com.tave.attendance.domain.member.repository.MemberRepository;
import com.tave.attendance.domain.session.entity.Session;
import com.tave.attendance.domain.session.exception.SessionNotFoundException;
import com.tave.attendance.domain.session.repository.SessionRepository;
import com.tave.attendance.domain.sessionmember.dto.MarkAttendanceReqDto;
import com.tave.attendance.domain.sessionmember.entity.SessionMember;
import com.tave.attendance.domain.sessionmember.entity.Status;
import com.tave.attendance.domain.sessionmember.exception.SessionMemberNotFoundException;
import com.tave.attendance.domain.sessionmember.repository.SessionMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final SessionRepository sessionRepository;
    private final MemberRepository memberRepository;
    private final SessionMemberRepository sessionMemberRepository;

    @Transactional
    public void markAttendance(Long sessionId, MarkAttendanceReqDto req) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(SessionNotFoundException::new);

        Member member = memberRepository.findByPhoneNumber(req.phoneNumber())
                .orElseThrow(PhonenumberMismatchException::new);

        SessionMember sm = sessionMemberRepository.findBySession_IdAndMember_Id(session.getId(), member.getId())
                .orElseThrow(SessionMemberNotFoundException::new);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime earlyBirdEnd = LocalDateTime.of(session.getSessionDate(), session.getEarlyBirdDeadline());
        LocalDateTime tardyStart   = LocalDateTime.of(session.getSessionDate(), session.getTardyTime());

        Status status;
        if (now.isBefore(earlyBirdEnd)) {
            status = Status.EARLY_BIRD;
        } else if (!now.isAfter(tardyStart)) {
            status = Status.ATTENDANCE;
        } else {
            status = Status.TARDY;
        }

        sm.mark(status, now);
    }
}
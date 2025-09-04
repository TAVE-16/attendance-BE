package com.tave.attendance.domain.session.service;

import com.tave.attendance.domain.member.entity.Member;
import com.tave.attendance.domain.member.exception.InvalidPhoneLast8Exception;
import com.tave.attendance.domain.member.exception.PhonenumberMismatchException;
import com.tave.attendance.domain.member.repository.MemberRepository;
import com.tave.attendance.domain.member.utils.PhoneNumberUtils;
import com.tave.attendance.domain.member.utils.ScoreUtils;
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

        String last8 = PhoneNumberUtils.last8(req.phoneNumber());
        if (last8.length() != 8) {
            throw new InvalidPhoneLast8Exception();
        }

        Member member = memberRepository.findByPhoneTail(last8)
                .orElseThrow(PhonenumberMismatchException::new);

        SessionMember sm = sessionMemberRepository.findBySessionIdAndMemberId(session.getId(), member.getId())
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

        int oldPenalty = ScoreUtils.penaltyOf(sm.getStatus(), sm.getAttendanceTime(), tardyStart);

        // 상태/체크시간을 이번값으로 반영
        sm.mark(status, now);

        // 새 페널티
        int newPenalty = ScoreUtils.penaltyOf(status, now, tardyStart);

        // 차이만 적용 (예: 이전 -10 → 이번 -30이면 delta = -20)
        int score = newPenalty - oldPenalty;
        if (score != 0) {
            member.applyScore(score);
        }
    }
}
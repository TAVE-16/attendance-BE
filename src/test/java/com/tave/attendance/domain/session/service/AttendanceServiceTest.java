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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AttendanceServiceTest {

    @Mock
    SessionRepository sessionRepository;
    @Mock
    MemberRepository memberRepository;
    @Mock
    SessionMemberRepository sessionMemberRepository;

    // 실제 서비스 클래스명에 맞게 수정하세요.
    AttendanceService attendanceService;

    @BeforeEach
    void setUp() {
        attendanceService = new AttendanceService(sessionRepository, memberRepository, sessionMemberRepository);
    }

    @Test
    void markAttendance_earlyBird면_EARLY_BIRD로_마킹() {
        // given
        Long sessionId = 1L;
        String phone = "010-1234-5678";

        Session session = mock(Session.class);
        Member member = mock(Member.class);
        SessionMember sm = mock(SessionMember.class);

        when(sessionRepository.findById(sessionId)).thenReturn(Optional.of(session));
        when(memberRepository.findByPhoneNumber(phone)).thenReturn(Optional.of(member));
        when(sessionMemberRepository.findBySessionIdAndMemberId(anyLong(), anyLong()))
                .thenReturn(Optional.of(sm));

        // 세션 시간 정보 (조정 가능)
        LocalDate sessionDate = LocalDate.of(2025, 9, 4);
        LocalTime earlyBirdDeadline = LocalTime.of(9, 30);
        LocalTime tardyTime = LocalTime.of(10, 10);

        when(session.getId()).thenReturn(sessionId);
        when(session.getSessionDate()).thenReturn(sessionDate);
        when(session.getEarlyBirdDeadline()).thenReturn(earlyBirdDeadline);
        when(session.getTardyTime()).thenReturn(tardyTime);

        // now 가 earlyBirdEnd 이전
        LocalDateTime now = LocalDateTime.of(2025, 9, 4, 9, 0);

        try (MockedStatic<LocalDateTime> mocked = mockStatic(LocalDateTime.class, CALLS_REAL_METHODS)) {
            mocked.when(LocalDateTime::now).thenReturn(now);

            // when
            attendanceService.markAttendance(sessionId, new MarkAttendanceReqDto(phone));

            // then
            verify(sm).mark(eq(Status.EARLY_BIRD), eq(now));
        }
    }

    @Test
    void markAttendance_정시면_ATTENDANCE로_마킹() {
        Long sessionId = 1L;
        String phone = "010-0000-0000";

        Session session = mock(Session.class);
        Member member = mock(Member.class);
        SessionMember sm = mock(SessionMember.class);

        when(sessionRepository.findById(sessionId)).thenReturn(Optional.of(session));
        when(memberRepository.findByPhoneNumber(phone)).thenReturn(Optional.of(member));
        when(sessionMemberRepository.findBySessionIdAndMemberId(anyLong(), anyLong()))
                .thenReturn(Optional.of(sm));

        LocalDate sessionDate = LocalDate.of(2025, 9, 4);
        LocalTime earlyBirdDeadline = LocalTime.of(9, 30);
        LocalTime tardyTime = LocalTime.of(10, 10);

        when(session.getId()).thenReturn(sessionId);
        when(session.getSessionDate()).thenReturn(sessionDate);
        when(session.getEarlyBirdDeadline()).thenReturn(earlyBirdDeadline);
        when(session.getTardyTime()).thenReturn(tardyTime);

        // now 가 earlyBirdEnd 이후이면서 tardyStart 보다 같거나 이른 시각
        LocalDateTime now = LocalDateTime.of(2025, 9, 4, 9, 45);

        try (MockedStatic<LocalDateTime> mocked = mockStatic(LocalDateTime.class, CALLS_REAL_METHODS)) {
            mocked.when(LocalDateTime::now).thenReturn(now);

            attendanceService.markAttendance(sessionId, new MarkAttendanceReqDto(phone));

            verify(sm).mark(eq(Status.ATTENDANCE), eq(now));
        }
    }

    @Test
    void markAttendance_지각이면_TARDY로_마킹() {
        Long sessionId = 1L;
        String phone = "010-1111-2222";

        Session session = mock(Session.class);
        Member member = mock(Member.class);
        SessionMember sm = mock(SessionMember.class);

        when(sessionRepository.findById(sessionId)).thenReturn(Optional.of(session));
        when(memberRepository.findByPhoneNumber(phone)).thenReturn(Optional.of(member));
        when(sessionMemberRepository.findBySessionIdAndMemberId(anyLong(), anyLong()))
                .thenReturn(Optional.of(sm));

        LocalDate sessionDate = LocalDate.of(2025, 9, 4);
        LocalTime earlyBirdDeadline = LocalTime.of(9, 30);
        LocalTime tardyTime = LocalTime.of(10, 10);

        when(session.getId()).thenReturn(sessionId);
        when(session.getSessionDate()).thenReturn(sessionDate);
        when(session.getEarlyBirdDeadline()).thenReturn(earlyBirdDeadline);
        when(session.getTardyTime()).thenReturn(tardyTime);

        // now 가 tardyStart 이후
        LocalDateTime now = LocalDateTime.of(2025, 9, 4, 10, 11);

        try (MockedStatic<LocalDateTime> mocked = mockStatic(LocalDateTime.class, CALLS_REAL_METHODS)) {
            mocked.when(LocalDateTime::now).thenReturn(now);

            attendanceService.markAttendance(sessionId, new MarkAttendanceReqDto(phone));

            verify(sm).mark(eq(Status.TARDY), eq(now));
        }
    }

    @Test
    void session_없으면_SessionNotFoundException() {
        Long sessionId = 99L;
        when(sessionRepository.findById(sessionId)).thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                attendanceService.markAttendance(sessionId, new MarkAttendanceReqDto("010-0000-0000"))
        ).isInstanceOf(SessionNotFoundException.class);
    }

    @Test
    void member_없으면_PhonenumberMismatchException() {
        Long sessionId = 1L;
        when(sessionRepository.findById(sessionId)).thenReturn(Optional.of(mock(Session.class)));
        when(memberRepository.findByPhoneNumber(anyString())).thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                attendanceService.markAttendance(sessionId, new MarkAttendanceReqDto("010-xxxx-xxxx"))
        ).isInstanceOf(PhonenumberMismatchException.class);
    }

    @Test
    void sessionMember_없으면_SessionMemberNotFoundException() {
        Long sessionId = 1L;
        Session session = mock(Session.class);
        when(sessionRepository.findById(sessionId)).thenReturn(Optional.of(session));
        when(memberRepository.findByPhoneNumber(anyString())).thenReturn(Optional.of(mock(Member.class)));
        when(sessionMemberRepository.findBySessionIdAndMemberId(anyLong(), anyLong()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                attendanceService.markAttendance(sessionId, new MarkAttendanceReqDto("010-1234-5678"))
        ).isInstanceOf(SessionMemberNotFoundException.class);
    }
}
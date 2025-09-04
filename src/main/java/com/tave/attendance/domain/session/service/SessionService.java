package com.tave.attendance.domain.session.service;

import com.tave.attendance.domain.member.entity.Member;
import com.tave.attendance.domain.member.repository.MemberRepository;
import com.tave.attendance.domain.session.dto.SessionCreateReqDto;
import com.tave.attendance.domain.session.dto.SessionDto;
import com.tave.attendance.domain.session.dto.SessionUpdateReqDto;
import com.tave.attendance.domain.session.entity.Session;
import com.tave.attendance.domain.session.exception.SessionNotFoundException;
import com.tave.attendance.domain.session.repository.SessionRepository;
import com.tave.attendance.domain.sessionmember.entity.SessionMember;
import com.tave.attendance.domain.sessionmember.entity.Status;
import com.tave.attendance.domain.sessionmember.repository.SessionMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final MemberRepository memberRepository;
    private final SessionMemberRepository sessionMemberRepository;

    public SessionDto create(SessionCreateReqDto req) {
        Session session = Session.builder()
                .title(req.title())
                .sessionDate(req.sessionDate())
                .tardyTime(req.tardyTime())
                .earlyBirdDeadline(req.earlyBirdDeadline())
                .seminarTime(req.seminarTime())
                .build();
        Session saved = sessionRepository.save(session);

        List<Member> members = memberRepository.findAll();
        List<SessionMember> links = members.stream()
                .map(m -> SessionMember.builder()
                        .session(saved)
                        .member(m)
                        .status(Status.ABSENT)
                        .build())
                .collect(Collectors.toList());
        sessionMemberRepository.saveAll(links);

        return toDto(saved);
    }

    public List<SessionDto> getAll() {
        return sessionRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public SessionDto getById(Long id) {
        return sessionRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(SessionNotFoundException::new);
    }

    @Transactional
    public void update(Long id, SessionUpdateReqDto req) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(SessionNotFoundException::new);

        session.update(
                req.title(),
                req.sessionDate(),
                req.tardyTime(),
                req.earlyBirdDeadline(),
                req.seminarTime()
        );
    }

    public void delete(Long id) {
        sessionRepository.deleteById(id);
    }

    private SessionDto toDto(Session session) {
        return new SessionDto(
                session.getId(),
                session.getTitle(),
                session.getSessionDate(),
                session.getTardyTime(),
                session.getEarlyBirdDeadline(),
                session.getSeminarTime()
        );
    }
}
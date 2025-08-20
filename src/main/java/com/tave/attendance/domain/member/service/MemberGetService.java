package com.tave.attendance.domain.member.service;

import com.tave.attendance.domain.member.entity.Member;
import com.tave.attendance.domain.member.entity.Role;
import com.tave.attendance.domain.member.exception.MemberNotFoundException;
import com.tave.attendance.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberGetService {

    private final MemberRepository memberRepository;

    public Member findMember(String email) {
        return memberRepository.findByEmailAndRole(email, Role.ADMIN)
                .orElseThrow(MemberNotFoundException::new);
    }

}

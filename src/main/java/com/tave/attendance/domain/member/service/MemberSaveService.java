package com.tave.attendance.domain.member.service;

import com.tave.attendance.domain.member.entity.Member;
import com.tave.attendance.domain.member.entity.Role;
import com.tave.attendance.domain.member.mapper.AdminMapper;
import com.tave.attendance.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.tave.attendance.domain.member.dto.AdminDto.*;


@Service
@RequiredArgsConstructor
public class MemberSaveService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final AdminMapper adminMapper;

    public void saveAdmin(AuthInfo saveInfo) {
        Member admin = adminMapper.of(saveInfo, Role.UN_AUTHORIZED, passwordEncoder);
        memberRepository.save(admin);
    }

}

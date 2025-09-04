package com.tave.attendance.domain.member.repository;

import com.tave.attendance.domain.member.entity.Member;
import com.tave.attendance.domain.member.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmailAndRole(String email, Role role);

    Optional<Member> findByPhoneNumber(String phoneNumber);

}

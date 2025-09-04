package com.tave.attendance.domain.member.repository;

import com.tave.attendance.domain.member.entity.Member;
import com.tave.attendance.domain.member.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmailAndRole(String email, Role role);

    Optional<Member> findByPhoneNumber(String phoneNumber);

    List<Member> findAllByRole(Role role);

    @Query(value = "SELECT * FROM member m WHERE RIGHT(m.phone_number_digits, 8) = :last8 LIMIT 1", nativeQuery = true)
    Optional<Member> findByPhoneTail(@Param("last8") String last8);

}

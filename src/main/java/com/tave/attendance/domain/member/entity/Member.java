package com.tave.attendance.domain.member.entity;

import com.tave.attendance.domain.member.utils.PhoneNumberUtils;
import com.tave.attendance.global.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Table
@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "username")
    String username;

    @Column(name = "phone_number", unique = true)
    String phoneNumber;

    @Column(name="phone_number_digits", length = 20)
    private String phoneNumberDigits;


    @Column(name = "email", unique = true)
    String email;

    @Column(name = "password")
    String password;

    @Column(name = "birthday")
    String birthday;

    @Column(name = "generation")
    String generation;

    @Column(name = "attendance_score")
    Long attendanceScore;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "field")
    FieldType field;

    @PrePersist @PreUpdate
    void normalizePhone() {
        this.phoneNumberDigits = PhoneNumberUtils.toDigits(this.phoneNumber);
    }

}
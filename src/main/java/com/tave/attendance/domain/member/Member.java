package com.tave.attendance.domain.member;

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

    @Column(name = "email", unique = true)
    String email;

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

}
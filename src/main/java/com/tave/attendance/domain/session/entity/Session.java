package com.tave.attendance.domain.session.entity;

import com.tave.attendance.domain.sessionmember.entity.SessionMember;
import com.tave.attendance.global.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "session")
@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Session extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 행사명
    @Column(name = "title", nullable = false)
    private String title;

    // 시작 날짜 (Date)
    @Column(name = "session_date", nullable = false)
    private LocalDate sessionDate;

    // 지각 시간 (Time)
    @Column(name = "tardy_time", nullable = false)
    private LocalTime tardyTime;

    // 얼리버드 종료 시간 (Time)
    @Column(name = "early_bird_deadline", nullable = false)
    private LocalTime earlyBirdDeadline;

    // 기술세미나 시간 (nullable Time)
    @Column(name = "seminar_time")
    private LocalTime seminarTime;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SessionMember> sessionMembers = new ArrayList<>();

    public void update(String title,
                       LocalDate sessionDate,
                       LocalTime tardyTime,
                       LocalTime earlyBirdDeadline,
                       LocalTime seminarTime) {
        if (title != null) this.title = title;
        if (sessionDate != null) this.sessionDate = sessionDate;
        if (tardyTime != null) this.tardyTime = tardyTime;
        if (earlyBirdDeadline != null) this.earlyBirdDeadline = earlyBirdDeadline;
        if (seminarTime != null) this.seminarTime = seminarTime;
    }
}

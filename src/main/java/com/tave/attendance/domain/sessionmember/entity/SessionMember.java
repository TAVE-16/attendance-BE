package com.tave.attendance.domain.sessionmember.entity;

import com.tave.attendance.domain.member.entity.Member;
import com.tave.attendance.domain.session.entity.Session;
import com.tave.attendance.global.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Table(
        name = "session_member",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_session_member", columnNames = {"session_id", "member_id"})
        }
)
@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SessionMember extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "attendance_time")
    private LocalDateTime attendanceTime;

    @PrePersist
    void onCreate() {
        if (status == null) status = Status.ABSENT; // 기본값
    }

    public void mark(Status status, LocalDateTime at) {
        this.status = status;
        this.attendanceTime = at;
    }
}
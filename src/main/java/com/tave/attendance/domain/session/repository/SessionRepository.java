package com.tave.attendance.domain.session.repository;

import com.tave.attendance.domain.session.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {

    Optional<Session> findBySessionDate(LocalDate date);
}
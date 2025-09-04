package com.tave.attendance.domain.session.repository;

import com.tave.attendance.domain.session.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
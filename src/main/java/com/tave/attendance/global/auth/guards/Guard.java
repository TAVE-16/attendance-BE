package com.tave.attendance.global.auth.guards;

import com.tave.attendance.domain.member.entity.Role;

public abstract class Guard {
    public final Role role;

    protected Guard(Role role) {
        this.role = role;
    }

}

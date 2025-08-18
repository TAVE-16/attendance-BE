package com.tave.attendance.global.auth.guards;

import com.tave.attendance.domain.member.Role;

public abstract class Guard {
    public final Role role;

    protected Guard(Role role) {
        this.role = role;
    }

}

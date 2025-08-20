package com.tave.attendance.global.auth.guards;

import com.tave.attendance.domain.member.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class MemberGuard extends Guard implements Authorizable {

    public MemberGuard() {
        super(Role.MEMBER);
    }

    @Override
    public boolean isAuthorized(Role role) {
        return this.role.equals(role);
    }

}

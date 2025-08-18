package com.tave.attendance.global.auth.guards;

import com.tave.attendance.domain.member.Role;

public interface Authorizable {

    boolean isAuthorized(Role role);

}

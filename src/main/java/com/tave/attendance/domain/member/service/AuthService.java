package com.tave.attendance.domain.member.service;

import com.tave.attendance.domain.member.exception.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    public void checkPassword(String rawPassword, String encodedPassword) {
        if(!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new AuthenticationException();
        }
    }

}

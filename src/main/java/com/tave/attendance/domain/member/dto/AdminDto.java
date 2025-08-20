package com.tave.attendance.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class AdminDto {

    public record AuthInfo(
            @NotNull
            @Email
            String email,

            @NotNull
            String password
    ) {
    }

}

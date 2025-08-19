package com.tave.attendance.domain.member.mapper;

import com.tave.attendance.domain.member.entity.Member;
import com.tave.attendance.domain.member.entity.Role;
import org.mapstruct.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.tave.attendance.domain.member.dto.AdminDto.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface AdminMapper {

    @Mappings({
            @Mapping(target = "password", expression = "java(passwordEncoder.encode(saveInfo.password()))"),
    })
    Member of(AuthInfo saveInfo, Role role, @Context PasswordEncoder passwordEncoder);

}

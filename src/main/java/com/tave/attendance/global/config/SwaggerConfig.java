package com.tave.attendance.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.tave.attendance.global.auth.constants.AuthConstant.AUTH_HEADER;

@Configuration
public class SwaggerConfig {

    private static final String SCHEMA_NAME = "bearerAuth";

    @Bean
    public OpenAPI openAPI() {
        SecurityScheme accessSecurityScheme = getAccessSecurityScheme();
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(SCHEMA_NAME);

        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .components(new Components()
                        .addSecuritySchemes(SCHEMA_NAME, accessSecurityScheme))
                .addSecurityItem(securityRequirement)
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Tave Attendance API Specifications")
                .description("Tave 출석 관리 시스템 REST API 스웨거 명세서")
                .version("1.0.0");
    }

    private SecurityScheme getAccessSecurityScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name(AUTH_HEADER);
    }
}
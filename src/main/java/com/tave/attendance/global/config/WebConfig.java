package com.tave.attendance.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.tave.attendance.global.auth.constants.AuthConstant.AUTH_HEADER;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AllowedOriginsConfig originConfig;

    /*
    * TODO
    * allowedOrigins "*" 대신 직접 명시하고 Credentials true로 변경
    * */

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(originConfig.getAllowedOrigins())
                .allowedMethods("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS", "HEAD")
                .allowedHeaders(AUTH_HEADER, "Cache-Control", "Content-Type")
                .exposedHeaders(AUTH_HEADER)
                .allowCredentials(false);
    }

}

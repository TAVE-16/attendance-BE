package com.tave.attendance.global.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AllowedOriginsConfig {

    public String[] getAllowedOrigins(){
        return new String[]{
            "*"
        };
    }

}

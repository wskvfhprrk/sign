package com.hejz.util.config;

import com.hejz.util.service.SignService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SignConfiguration {
    @Bean
    public SignService signService() {
        return new SignService();
    }
}

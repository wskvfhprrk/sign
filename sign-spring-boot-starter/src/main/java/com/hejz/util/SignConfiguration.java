package com.hejz.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SignConfiguration {
    @Bean
    public SignatureService signatureService() {
        return new SignatureService();
    }
}

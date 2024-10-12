package com.hejz.config;

import com.hejz.service.SignatureService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.hejz.service"})
public class SignatureAutoConfiguration {
//    @Bean
//    public SignatureService signatureService() {
//        System.out.println("---------------->signatureService");
//        return new SignatureService();
//    }
}


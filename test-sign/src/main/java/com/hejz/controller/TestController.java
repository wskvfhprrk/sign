package com.hejz.controller;


import com.hejz.service.SignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TestController {

    @Autowired
    private SignatureService signatureService;

    @GetMapping("/test")
    public String test() throws Exception {
        return signatureService.signData("231","456",System.currentTimeMillis(), UUID.randomUUID().toString());
    }
}

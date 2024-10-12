package com.hejz.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private SignatureService signatureService;
    @GetMapping("test")
    public String test() throws Exception {
        return signatureService.signData("231","645");
    }
}

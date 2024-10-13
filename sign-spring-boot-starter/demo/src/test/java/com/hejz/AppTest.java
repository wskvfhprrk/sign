package com.hejz;

import com.alibaba.fastjson.JSON;
import com.hejz.util.dto.VerifyDto;
import com.hejz.util.service.SignService;
import com.hejz.util.vo.SignVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AppTest {
    @Autowired
    private SignService signService;

    @Test
    public void test() throws Exception {
        String secretKey="1111";
        SignVo signVo = signService.signByData("123", secretKey);
        VerifyDto dto=new VerifyDto();
        BeanUtils.copyProperties(signVo,dto);
        Boolean aBoolean = signService.verifyData(dto, secretKey);
        System.out.println("--------->"+aBoolean);
        boolean b = signService.verifyData(JSON.toJSONString(signVo), secretKey);
        System.out.println("++++++++++++++++++"+b);
        boolean b1 = signService.verifyData(signVo.getSign(),signVo.getData(), signVo.getTimestamp(), signVo.getNonce(),  secretKey);
        System.out.println("++++++++++++++++++"+b1);
    }
}

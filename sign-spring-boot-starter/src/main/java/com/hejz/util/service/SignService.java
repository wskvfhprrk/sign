package com.hejz.util.service;


import com.hejz.util.SignatureUtil;
import com.hejz.util.dto.SignDto;
import com.hejz.util.dto.VerifyDto;
import com.hejz.util.vo.SignVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SignService {
    // 生成签名，带时间戳和随机字符串
    public String signData(String data, String secretKey, long timestamp, String nonce) throws Exception {
        return SignatureUtil.generateSignature(data, secretKey, timestamp, nonce);
    }

    public String signData(SignDto dto) throws Exception {
        return signData(dto.getData(), dto.getSecretKey(), dto.getTimestamp(), dto.getNonce());

    }

    public SignVo signDataToVo(SignDto dto) throws Exception {
        String sign = signData(dto.getData(), dto.getSecretKey(), dto.getTimestamp(), dto.getNonce());
        SignVo vo = new SignVo();
        BeanUtils.copyProperties(dto, vo);
        vo.setSignature(sign);
        return vo;
    }

    // 验证签名，检查时间戳、随机字符串和数据完整性
    public boolean verifyData(String data, String sign, String secretKey, long timestamp, String nonce) throws Exception {
        return SignatureUtil.verifySignature(data, sign, secretKey, timestamp, nonce);
    }

    public Boolean verifyData(VerifyDto dto) throws Exception {
        return verifyData(dto.getData(), dto.getSecretKey(), dto.getSignature(), dto.getTimestamp(), dto.getNonce());
    }
}

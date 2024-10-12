package com.hejz.service;


import com.hejz.entity.SignDto;
import com.hejz.entity.VerifyDto;
import com.hejz.util.SignatureUtil;
import org.springframework.stereotype.Service;

@Service
public class SignatureService {
    /**
     * 数据加密
     *
     * @param data      原妈数据
     * @param secretKey 密钥
     * @return 签名字符串
     * @throws Exception
     */
    public String signData(String data, String secretKey, long timestamp, String nonce) throws Exception {
        return SignatureUtil.generateSignature(data, secretKey, timestamp, nonce);
    }

    public String signData(SignDto signDto) throws Exception {
        return signData(signDto.getData(),signDto.getSecretKey(),signDto.getTimestamp(),signDto.getNonce());
    }

    /**
     * 解密
     *
     * @param data      原妈数据
     * @param signature 签名字符串
     * @param secretKey 密钥
     * @return
     * @throws Exception
     */
    public boolean verifyData(String data, String signature, String secretKey, long timestamp, String nonce) throws Exception {
        return SignatureUtil.verifySignature(data, secretKey, signature, timestamp, nonce);
    }

    /**
     *
     * @param verifyDto
     * @return
     * @throws Exception
     */
    public Boolean verifyData(VerifyDto verifyDto) throws Exception {
        return verifyData(verifyDto.getData(),verifyDto.getSign(),verifyDto.getSecretKey(),verifyDto.getTimestamp(),verifyDto.getNonce());
    }
}

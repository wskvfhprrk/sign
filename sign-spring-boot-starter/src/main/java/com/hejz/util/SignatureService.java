package com.hejz.util;


public class SignatureService {


    // 生成签名，带时间戳和随机字符串
    public String signData(String data,String secretKey) throws Exception {
        long timestamp = System.currentTimeMillis(); // 当前时间戳
        String nonce = SignatureUtil.generateNonce(16); // 生成16位随机字符串
        return SignatureUtil.generateSignature(data, secretKey, timestamp, nonce) + ":" + timestamp + ":" + nonce;
    }

    // 验证签名，检查时间戳、随机字符串和数据完整性
    public boolean verifyData(String data, String signatureData,String secretKey) throws Exception {
        String[] parts = signatureData.split(":");
        if (parts.length != 3) {
            return false; // 格式不正确
        }

        String signature = parts[0];
        long timestamp = Long.parseLong(parts[1]);
        String nonce = parts[2];

        return SignatureUtil.verifySignature(data, secretKey, signature, timestamp, nonce);
    }
}

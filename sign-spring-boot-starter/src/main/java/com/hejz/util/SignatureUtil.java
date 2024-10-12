package com.hejz.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;

public class SignatureUtil {
    /**
     * 签名数据：data + timestamp + nonce + secretKey
     * 生成签名：使用 HMAC-SHA256 加密生成签名。
     * 时间有效性：签名创建时会带上时间戳，验证时检查时间是否在10分钟（600秒）以内。
     * 随机字符串（nonce）：每次生成一个随机字符串，以避免重放攻击。
     */

    private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";
    private static final long MAX_VALID_DURATION = 10 * 60 * 1000; // 10分钟的毫秒数

    // 生成HMAC SHA256签名
    public static String generateSignature(String data, String secretKey, long timestamp, String nonce) throws Exception {
        String message = data + timestamp + nonce + secretKey;
        Mac sha256_HMAC = Mac.getInstance(HMAC_SHA256_ALGORITHM);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), HMAC_SHA256_ALGORITHM);
        sha256_HMAC.init(secretKeySpec);
        byte[] signedBytes = sha256_HMAC.doFinal(message.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(signedBytes);
    }

    // 验证签名是否有效
    public static boolean verifySignature(String data, String secretKey, String signature, long timestamp, String nonce) throws Exception {
        // 检查时间戳是否在10分钟内
        long currentTime = System.currentTimeMillis();
        if (Math.abs(currentTime - timestamp) > MAX_VALID_DURATION) {
            return false; // 超过10分钟，签名失效
        }

        // 生成新的签名并与传入的签名进行对比
        String generatedSignature = generateSignature(data, secretKey, timestamp, nonce);
        return generatedSignature.equals(signature);
    }

    // 生成随机字符串 (nonce)
    public static String generateNonce(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder nonce = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            nonce.append(characters.charAt(random.nextInt(characters.length())));
        }
        return nonce.toString();
    }
}

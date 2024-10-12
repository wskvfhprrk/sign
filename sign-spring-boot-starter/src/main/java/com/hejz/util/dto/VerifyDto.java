package com.hejz.util.dto;

/**
 * 解密所需要参数
 */
public class VerifyDto {
    /**
     * 数据字符串
     */
    private String data;
    /**
     * 时间戳
     */
    private long timestamp;
    /**
     * 一次性数据
     */
    private String nonce;
    /**
     * 加密字符串
     */
    private String sign;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}

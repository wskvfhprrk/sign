package com.hejz.util.dto;

/**
 * 加密所需要参数
 */
public class SignDto {
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
}

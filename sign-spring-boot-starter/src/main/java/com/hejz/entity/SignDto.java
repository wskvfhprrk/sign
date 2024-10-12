package com.hejz.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignDto {
    /**
     * 数据
     */
    private String data;
    /**
     * 密钥
     */
    private String secretKey;
    /**
     * 一次性字符串
     */
    private String nonce;
    /**
     * 时间戳——超过10分钟密码无效
     */
    private Long timestamp;
}

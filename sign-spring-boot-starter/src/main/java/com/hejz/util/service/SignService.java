package com.hejz.util.service;


import com.alibaba.fastjson.JSON;
import com.hejz.util.SignUtil;
import com.hejz.util.dto.SignDto;
import com.hejz.util.dto.VerifyDto;
import com.hejz.util.vo.SignVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


@Service
public class SignService {
    /**
     *  生成签名
     * @param data 数据字符串
     * @param secretKey 密钥
     * @param timestamp 时间嶻
     * @param nonce 一次数据
     * @return 加密签名sign
     * @throws Exception
     */
    public String signData(String data, String secretKey, long timestamp, String nonce) throws Exception {
        return SignUtil.generateSignature(data, secretKey, timestamp, nonce);
    }

    /**
     * 生成签名
     * @param dto 签名参数——未含密钥
     * @param secretKey 密钥
     * @return 加密签名sign
     * @throws Exception
     */
    public String signData(SignDto dto, String secretKey) throws Exception {
        return signData(dto.getData(), secretKey, dto.getTimestamp(), dto.getNonce());

    }

    /**
     * 生成签名
     * @param dto 签名参数——未含密钥
     * @param secretKey 密钥
     * @return 带有签名的返回类，可以直接变为字符串传送
     * @throws Exception
     */
    public SignVo signDataToVo(SignDto dto , String secretKey) throws Exception {
        String sign = signData(dto.getData(), secretKey, dto.getTimestamp(), dto.getNonce());
        SignVo vo = new SignVo();
        BeanUtils.copyProperties(dto, vo);
        vo.setSign(sign);
        return vo;
    }

    /**
     * 生成签名，自动生成带时间戳和随机字符串
     * @param data 需要加密数据
     * @param secretKey
     * @return 带有签名的返回类，可以直接变为字符串传送
     * @throws Exception
     */
    public SignVo signByData(String data, String secretKey)throws Exception{
        long millis = System.currentTimeMillis();
        String nonce = SignUtil.generateNonce(16);
        String sign = signData(data, secretKey, millis, nonce);
        SignVo vo = new SignVo();
        vo.setData(data);
        vo.setTimestamp(millis);
        vo.setNonce(nonce);
        vo.setSign(sign);
        return vo;
    }

    /**
     * 验证签名，检查时间戳、随机字符串和数据完整性
     * @param data 加密的数据
     * @param sign 加密的签名
     * @param secretKey 密钥
     * @param timestamp 时间嶻
     * @param nonce 一次怀数据
     * @return 验证通过就为真，不通过就是假
     * @throws Exception
     */
    public boolean verifyData(String data, String sign, String secretKey, long timestamp, String nonce) throws Exception {
        return SignUtil.verifySignature(data, secretKey, sign, timestamp, nonce);
    }

    /**
     * 验证签名，检查时间戳、随机字符串和数据完整性
     * @param dto 验证通过的实体类
     * @param secretKey 密钥
     * @return 验证通过就为真，不通过就是假
     * @throws Exception
     */
    public Boolean verifyData(VerifyDto dto, String secretKey) throws Exception {
        return verifyData(dto.getData(), secretKey, dto.getSign(), dto.getTimestamp(), dto.getNonce());
    }
    /**
     * 验证签名，检查时间戳、随机字符串和数据完整性
     * @param data 需要签名验证的字符串
     * @param secretKey 密钥
     * @return 验证通过就为真，不通过就是假
     * @throws Exception
     */
    public boolean verifyData(String data,String secretKey) throws Exception {
        VerifyDto dto = JSON.parseObject(data, VerifyDto.class);
        String sign=dto.getSign();
        long timestamp=dto.getTimestamp();
        String nonce=dto.getNonce();
        return SignUtil.verifySignature(data, secretKey, sign, timestamp, nonce);
    }
}

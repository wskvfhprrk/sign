package com.guardlbt.sign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SignUtil {
        public static final Logger LOGGER = LoggerFactory.getLogger(com.guardlbt.sign.SignUtil.class);

        /**
         * 生成要请求给平台的参数数组
         *
         * @param sParaTemp 请求前的参数数组
         * @return 要请求的参数数组
         */
        public static Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
            //除去数组中的空值和签名参数
            Map<String, String> sPara = SignCore.paraFilter(sParaTemp);
            String prestr = SignCore.createLinkString(sPara); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
            //生成签名结果
            String mysign = SignMD5.sign(prestr, SignConfig.APP_SECRET, SignConfig.INPUT_CHARSET_TYPE);

            //签名结果与签名方式加入请求提交参数组中
            sPara.put("sign", mysign);
            sPara.put("sign_method", SignConfig.SIGN_METHOD);

            return sPara;
        }

        /**
         * @throws
         * @Title:verify
         * @Description:验证签名结果是否一致
         * @param:@param sParaTemp
         * @param:@param sign
         * @param:@return
         * @return:boolean
         * @Create: 2016年10月25日 下午4:32:29
         * @Author : jason
         */
        public static boolean verifySign(Map<String, String> sParaTemp, String sign) {
            //除去数组中的空值和签名参数
            Map<String, String> sPara = SignCore.paraFilter(sParaTemp);

            String prestr = SignCore.createLinkString(sPara); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
            //生成签名结果
            String mysign = SignMD5.sign(prestr, SignConfig.APP_SECRET, SignConfig.INPUT_CHARSET_TYPE);

            LOGGER.info("签名结果:" + mysign);
            if (mysign.equalsIgnoreCase(sign)) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * 验证参数
         *
         * @param sParaTemp 包含有sign参数，不需要取出来再验证
         * @return
         */
        public static boolean verifySign2(Map<String, String> sParaTemp) {
            //除去数组中的空值和签名参数
            Map<String, String> sPara = new HashMap<>();
            String sign = "";
            if (sParaTemp == null || sParaTemp.size() <= 0) {
                return false;
            }
            Iterator iterator = sParaTemp.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                Object key = entry.getKey();
                Object value = entry.getValue();
                if (value == null || "".equals(value)
                        || key.equals(SignConfig.PARAM_SIGN_METHOD) || key.equals(SignConfig.PARAM_SIGN)) {
                    if (key.equals(SignConfig.PARAM_SIGN)) {
                        sign = (String) value;
                    }
                    continue;
                }
                sPara.put(key.toString(), value.toString());
            }
            //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
            String prestr = SignCore.createLinkString(sPara);
            //生成签名结果
            String mysign = SignMD5.sign(prestr, SignConfig.APP_SECRET, SignConfig.INPUT_CHARSET_TYPE);
            LOGGER.info("签名结果:" + mysign);
            if (mysign.equalsIgnoreCase(sign)) {
                return true;
            } else {
                return false;
            }
        }

        //以下根据request获取所有参数的方法(不适用于multipart/form-data)
        public static Map getAllParams(HttpServletRequest request) {
            Enumeration<String> enumeration = request.getParameterNames();
            Map<String, String> map = new HashMap<>();
            String key;
            while (enumeration.hasMoreElements()) {
                key = enumeration.nextElement();
                map.put(key, request.getParameter(key));

            }
            return map;
        }
}

# sign
签名验证

添加依赖或加载依赖包

```xml
 		<dependency>
            <groupId>commons-codec</groupId>
            <artifactId>commons-codec</artifactId>
            <version>1.8</version>
        </dependency>
```

signUtil类为验签工具类，参数以Map对象存储后，使用方法得到sing值：

```java
Map<String, String> sParaTemp = new HashMap<>();
//把所有参数放进map中（加入所有参数）
//把当前时间戳入进去
sParaTemp.put("time",String.valueOf(System.currentTimeMillis()));
//生成包含有sign参数的map——stringStringMap
Map<String, String> signMap = SignUtil.buildRequestPara(sParaTemp);
System.out.println("sign:"+signMap.get("sign"));
```

得到sign后，把所有参数signMap，以**post方式**请求后台，注意参数不能是json，以表单提交方式参数；
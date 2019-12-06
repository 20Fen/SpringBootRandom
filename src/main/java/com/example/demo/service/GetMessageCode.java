package com.example.demo.service;

import com.example.demo.util.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 调用手机短信API
 */
@Service
public class GetMessageCode {

    @Autowired
    private Jedis jedis;
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Value(value = "templateCode")
    private String template_code;
    @Value(value = "signName")
    private String sign_name;
    private static String code= Code.randomCode();   //生成一个随机验证码
    private static String smsContent = "【严科伟】您的验证码："+code+"，如非本人操作，请忽略此短信。";

    public void getCode(String phone) throws Exception {

        Map map=new HashMap<>();
        if(StringUtils.isEmpty(phone)){
            throw new Exception("手机号不能为空");
        }

        map.put("Phone",phone);
        map.put("templateCode",template_code);
        map.put("signName",sign_name);
        map.put("param", "{\"smsContent\":\"code\"}");
        jmsMessagingTemplate.send("sms", (Message<?>) map);
    }
}

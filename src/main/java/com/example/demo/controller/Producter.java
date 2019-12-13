package com.example.demo.controller;

import com.example.demo.model.Cheak;
import com.example.demo.util.CodeUtil;
import com.example.demo.util.JedisUtil;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import javax.jms.Destination;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 订阅模式生产者
 */
@Configuration
public class Producter {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Value(value = "${appKey}")
    private String appKey;
    @Value(value = "${tpl_id}")
    private String tplId;

    public void getTopic(String phone) throws Exception {

        if (StringUtils.isEmpty(phone)) {
            throw new Exception("手机号不能为空");
        }

        Map map = new HashMap();//请求参数

        //生产验证码
        String code = CodeUtil.randomCode();
        map.put("mobile", phone);//接受短信的用户手机号码
        map.put("tpl_id", tplId);//您申请的短信模板ID，根据实际情况修改
        map.put("tpl_value", "#code#=" + code);//验证码
        map.put("key", appKey);//应用APPKEY(应用详细页查询)
        ActiveMQTopic topic = new ActiveMQTopic("t");
        jmsMessagingTemplate.convertAndSend(topic, map);
    }

    public void getQueue(String phone) throws Exception {

        if (StringUtils.isEmpty(phone)) {
            throw new Exception("手机号不能为空");
        }

        Map map = new HashMap();//请求参数
        String code = CodeUtil.randomCode();
        JedisUtil.setValue(phone, code, 60);
        //生产验证码
        map.put("mobile", phone);//接受短信的用户手机号码
        map.put("tpl_id", tplId);//您申请的短信模板ID，根据实际情况修改
        map.put("tpl_value", "#code#=" + code);//验证码
        map.put("key", appKey);//应用APPKEY(应用详细页查询)
        Destination queue = new ActiveMQQueue("q");
        jmsMessagingTemplate.convertAndSend(queue, map);
    }

    public String cheak(Cheak cheak) throws Exception {

        if (null == cheak) {
            throw new Exception("参数不能为空");
        }

        Object code = JedisUtil.getValue(cheak.getPhone());
        if (code.equals(cheak.getCode())) {
            return "0";
        }
        throw new Exception("验证码不一致，请确认验证码是否正确");
    }
}


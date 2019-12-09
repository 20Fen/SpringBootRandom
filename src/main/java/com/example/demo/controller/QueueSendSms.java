package com.example.demo.controller;

import com.example.demo.util.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author yangfl
 * @date 2019年12月09日 14:36
 * Version 1.0
 */
@RestController
@RequestMapping("/send")
public class QueueSendSms {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    private String userAgent;
    @Value(value = "${appKey}")
    private String appKey;
    @Value(value = "${tpl_id}")
    private String tplId;

    @RequestMapping("/code")
    public void getCode(String phone) throws IOException {

        Map map = new HashMap();//请求参数
        //生产验证码
        String code = CodeUtil.randomCode();
        String result =null;
        map.put("mobile",phone);//接受短信的用户手机号码
        map.put("tpl_id",tplId);//您申请的短信模板ID，根据实际情况修改
        map.put("tpl_value","#code#="+code);//验证码
        map.put("key",appKey);//应用APPKEY(应用详细页查询)
        jmsMessagingTemplate.convertAndSend("Y",map);

    }
}

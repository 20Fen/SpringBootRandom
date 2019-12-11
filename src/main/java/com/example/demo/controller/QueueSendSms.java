package com.example.demo.controller;

import com.example.demo.util.CodeUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:发送短信
 */
@RestController
@RequestMapping("/send")
@Api(value = "发送短信")
public class QueueSendSms {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    private String userAgent;
    @Value(value = "${appKey}")
    private String appKey;
    @Value(value = "${tpl_id}")
    private String tplId;

    @PostMapping("/code")
    public void getCode(String phone) {

        if(StringUtils.isEmpty(phone)){
            return;
        }

        Map map = new HashMap();//请求参数

            //生产验证码
            String code = CodeUtil.randomCode();
            map.put("mobile",phone);//接受短信的用户手机号码
            map.put("tpl_id",tplId);//您申请的短信模板ID，根据实际情况修改
            map.put("tpl_value","#code#="+code);//验证码
            map.put("key",appKey);//应用APPKEY(应用详细页查询)
            jmsMessagingTemplate.convertAndSend("Y",map);
    }
}

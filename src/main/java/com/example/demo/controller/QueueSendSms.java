package com.example.demo.controller;

import com.example.demo.util.CodeUtil;
import io.swagger.annotations.Api;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTempTopic;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;
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
    private Producter producter;



    @PostMapping("/queue")
    public void getQueue(String phone) throws Exception {
        producter.getQueue(phone);
    }
    @PostMapping("/topic")
    public void getTopic(String phone) throws Exception {
        producter.getTopic(phone);
    }

}

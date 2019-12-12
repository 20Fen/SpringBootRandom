package com.example.demo.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    public String getQueue(String phone) throws Exception {
        final String result = producter.getQueue(phone);
        return result;
    }
    @PostMapping("/topic")
    public void getTopic(String phone) throws Exception {
        producter.getTopic(phone);
    }

}

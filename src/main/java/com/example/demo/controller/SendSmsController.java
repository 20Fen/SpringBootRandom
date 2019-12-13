package com.example.demo.controller;

import com.example.demo.model.Cheak;
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
public class SendSmsController {

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

    @PostMapping("/cheak")
    public String cheak(Cheak cheak) throws Exception {
        String cheak1 = producter.cheak(cheak);
        if("0".equals(cheak1)){
            return "0";
        }else {
            return "-1";
        }
    }

}

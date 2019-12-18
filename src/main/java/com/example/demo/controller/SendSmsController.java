package com.example.demo.controller;

import com.example.demo.model.Cheak;
import com.example.demo.model.Phones;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * Description:发送短信
 */
@RestController
@RequestMapping("/send")
@Api(value = "发送短信")
public class SendSmsController{

    @Autowired
    private Producter producter;



    @PostMapping("/queue")
    public void getQueue(@Valid @RequestBody Phones phone, BindingResult result){
        phone.validate(result);
         producter.getQueue(phone.getPhone());
    }

    @PostMapping("/topic")
    public void getTopic(@Valid @RequestBody Phones phone, BindingResult result) {
        phone.validate(result);
        producter.getTopic(phone.getPhone());
    }

    @PostMapping("/cheak")
    public String cheak(@Valid @RequestBody Cheak cheak, BindingResult result) {
        cheak.validate(result);
        String cheak1 = producter.cheak(cheak);
        if("0".equals(cheak1)){
            return "0";
        }else {
            return "-1";
        }
    }

}

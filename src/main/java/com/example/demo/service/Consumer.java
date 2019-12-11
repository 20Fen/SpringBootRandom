package com.example.demo.service;

import com.example.demo.util.JuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Description:
 *
 * @author yangfl
 * @date 2019年12月09日 14:42
 * Version 1.0
 */
@Component
public class Consumer {


    @Autowired
    private JuUtil juUtil;

    @Value(value = "${url}")
    private String url;

    @JmsListener(destination = "Y")
    public void readMap(Map map){
        System.out.println(map);
    }

    @JmsListener(destination = "Y")
    public void sendSms(Map<String,String> map){
                map.get("mobile");
                map.get("tpl_id");
                map.get("tpl_value");
                map.get("key");

        String post = null;
        try {
            post = juUtil.net(url, map, "POST");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(post);
    }
}

package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.GetMessage;
import com.example.demo.util.Code;
import com.example.demo.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;


/**
 * Description:
 *
 * @author yangfl
 * @date 2019年12月06日 17:13
 * Version 1.0
 */
@Service
public class GetMessageImpl implements GetMessage {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    //对应的API地址
    @Value(value = "${ACCOUNT_SID}")
    private String operation;

    @Value(value = "${operation}")
    private  String accountSid;

    @Value(value = "${BASE_URL}")
    private  String BASEURL;

    @Value(value = "${RESP_DATA_TYPE}")
    private  String RESPDATATYPE;
    private static String to = "17121192629"; //改由前台传入
    private static String rod=Code.randomCode();   //生成一个随机验证码
    private static String smsContent = rod;

    @Override
    public String  getCode(String phone) throws Exception {

        String url = BASEURL + operation;
        String body = "accountSid=" + accountSid + "&to=" + phone + "&smsContent=" + smsContent
                + HttpUtil.createCommonParam();

        // 提交请求
        String result = HttpUtil.post(url, body);

        //(换行符) 剔除了平台无关性
        System.out.println("result:" + System.lineSeparator() + result);
        System.out.println(result.getClass());

        //字符串转json对象
        JSONObject jsonObject = JSONObject.parseObject(result);
        String respCode = jsonObject.getString("respCode");
        System.out.println(respCode);

        //反馈-00000状态码标识请求成功，
        String defaultRespCode="00000";
        if(defaultRespCode.equals(respCode)){
            return rod;
        }else{
            return defaultRespCode;
        }
    }
    }


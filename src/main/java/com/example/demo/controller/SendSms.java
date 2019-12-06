package com.example.demo.controller;

import com.example.demo.service.GetMessageCode;
import com.example.demo.util.Code;
import com.example.demo.util.ImageCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @description controller类
 * @author Y
 * @Param [ null ]
 * @return
 * @date 2019/10/14 10:51
 */
@Log4j2
@RestController
@RequestMapping("/sendSms")
public class SendSms {

    @Autowired
    private GetMessageCode getMessageCode;

    @PostMapping()
    public void getImageCode(String phone) throws Exception {

        getMessageCode.getCode(phone);

    }
}


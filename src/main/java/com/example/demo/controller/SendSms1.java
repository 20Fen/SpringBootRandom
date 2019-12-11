package com.example.demo.controller;

import com.example.demo.util.CodeUtil;
import com.example.demo.util.ImageCode;
import lombok.extern.log4j.Log4j2;
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
 */
@Log4j2
@RestController
@RequestMapping("/sendSms")
public class SendSms1 {

    @RequestMapping("/imagecode")
    public void getImageCode(HttpServletResponse response, HttpServletRequest request) throws IOException {

        int width = 200;
        int height = 69;
        BufferedImage verifyImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//生成对应宽高的初始图片
        String randomText = ImageCode.drawRandomText(width, height, verifyImg);
//单独的一个类方法，出于代码复用考虑，进行了封装。
//功能是生成验证码字符并加上噪点，干扰线，返回值为验证码字符
//        request.getSession().setAttribute("verifyCode", randomText);
        response.setContentType("image/png");//必须设置响应内容类型为图片，否则前台不识别
        OutputStream os = response.getOutputStream(); //获取文件输出流
        ImageIO.write(verifyImg, "png", os);//输出图片流
        os.flush();
        os.close();//关闭流

    }
    @RequestMapping("/code")
    public StringBuffer getCode() {

        StringBuffer stringBuffer = CodeUtil.drawRandomText();
        return stringBuffer;
    }

    @RequestMapping("/intcode")
    public String code() {
        String randomCode = CodeUtil.randomCode();
        return randomCode;
    }
}


package com.example.demo.util;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @description 随机验证码类
 * @author Y
 * @Param [ null ]
 * @return
 * @date 2019/10/14 10:38
 */
@Component
public class CodeUtil {
    public static StringBuffer drawRandomText() {
        StringBuffer sBuffer = new StringBuffer();
        //数字和字母的组合
        String baseNumLetter =  "123456789abcdefghijklmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";
        String ch = "";
        Random random = new Random();
        for(int i = 0;i < 4;i++){
            int dot = random.nextInt(baseNumLetter.length());
            ch = baseNumLetter.charAt(dot) + "";
            sBuffer.append(ch);
        }
       return sBuffer;
    }

    public static String randomCode() {
        System.out.println(Math.random());
        //        生成随机的六位数字
        String value = (int) ((Math.random()* 9) * 100000)+"";
        System.out.println(value);
        //        生成随机的六位数字
//        String u = Integer.valueOf((int) ((Math.random() * 9) * 100000))+"";
//        System.out.println(u);
        return value;
    }
}

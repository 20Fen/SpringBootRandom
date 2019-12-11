package com.example.demo.service.impl;

import com.example.demo.util.CodeUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 聚合测试
 */
public class Juhe {

    @Value(value = "${DEFCHATSET}")
    private String DEFCHATSET;
    @Value(value = "${DEFCONNTIMEOUT}")
    private int DEFCONNTIMEOUT;
    @Value(value = "${DEF_READ_TIMEOUT}")
    private int DEFREADTIMEOUT;
    @Value(value = "${userAgent}")
    private String userAgent;
    @Value(value = "${appKey}")
    private String appKey;
    @Value(value = "${tpl_id}")
    private String tplId;
    @Value(value = "${url}")
    private String url;

    public void mobileQuery(String phone){

        Map map = new HashMap();//请求参数
        //生产验证码
        String code = CodeUtil.randomCode();
        String result =null;
			map.put("mobile",phone);//接受短信的用户手机号码
            map.put("tpl_id",tplId);//您申请的短信模板ID，根据实际情况修改
            map.put("tpl_value","#code#="+code);//验证码
			map.put("key",appKey);//应用APPKEY(应用详细页查询)

        try {
            result = net(url, map, "POST");

            com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(result);
            System.out.println(jsonObject);

            JSONObject object = JSONObject.fromObject(result);
            System.out.println(object);
        if(object.getInt("error_code")==0){
            System.out.println(object.get("result"));
        }else{
            System.out.println(object.get("error_code")+":"+object.get("reason"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }


    public void main(String[] args) {
        mobileQuery("15622969639");
    }

    /**
     *
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return  网络请求字符串
     * @throws Exception
     */
    public String net(String strUrl, Map params, String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEFCONNTIMEOUT);
            conn.setReadTimeout(DEFREADTIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params!= null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEFCHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    //将map型转为请求参数型
    public String urlencode(Map<String, String> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}

package com.ybybcsgo.backend.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.ybybcsgo.backend.utils.Secrets.BOT_WEBHOOK_AUTH_HEADER_STRING;

public class Http {

    public static String getHtmlByPost(String urlString, String postBody) throws IOException {
        byte[] xmlData = postBody.getBytes("UTF-8");

        //接收想要连接网址的地址
        URL url = new URL(urlString);
        //响应
        StringBuilder responseBuilder=new StringBuilder();
        //读取信息为文本信息，所以用bufferReader
        BufferedReader reader=null;
        //用url对象打开连接(仅仅打开了连接，并未发送请求)
        HttpURLConnection conn=(HttpURLConnection)url.openConnection();
        //设置HttpURLConnection参数
        //设置post方法
        conn.setRequestMethod("POST");
        //不使用缓存
        conn.setUseCaches(false);
        conn.setDefaultUseCaches(false);
        //post请求必须设置如下2行
        conn.setDoInput(true);
        conn.setDoOutput(true);
        //读取超时时间
        conn.setReadTimeout(5000);
        conn.setConnectTimeout(5000);
        //设置不要302自动跳转，防止重定向
        conn.setInstanceFollowRedirects(false);
        //设置传入参数的格式
        conn.setRequestProperty("Authorization", BOT_WEBHOOK_AUTH_HEADER_STRING);
        conn.setRequestProperty("Content-Type","application/json;UTF-8");
        //没写可能出现411错误
        conn.setRequestProperty("Content-Length",String.valueOf(xmlData.length));
        //通过连接对象获取一个输出流
        DataOutputStream printout = new DataOutputStream(conn.getOutputStream());
        //输出流发送请求参数
        printout.write(xmlData);
        //flush输出流的缓冲
        printout.flush();
        printout.close();

        //定义BufferedReader输入流来读取URL的响应

        int code=conn.getResponseCode();
        System.out.printf("[Http] [getHtmlByPost] Response code: %d\n", code);

        if(code==200) {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            //逐行读取数据
            String line = null;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line + "\n");
            }
            reader.close();
        }else{
            conn.getErrorStream();
        }

        return  responseBuilder.toString();
    }
}

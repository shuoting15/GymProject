package com.gym.member.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import java.io.PrintWriter;
import java.util.List;

public class HttpUtil {
	
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打開和 URL 之間的連接
            URLConnection connection = realUrl.openConnection();
            // 設置通用的請求屬性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立實際的連接
            connection.connect();
            // 獲得所有回應Header
            Map<String, List<String>> map = connection.getHeaderFields();
            // 印出所有的Header
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定義 BufferedReader 來讀取 URL 的回應
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("發送 GET 請求出現異常!" + e);            
        }
        
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                
            }
        }
        return result;
    }
            
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打開和 URL 之間的連接
            URLConnection conn = realUrl.openConnection();
            // 設置通用的請求屬性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 發送POST必須設置下兩行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 獲得URLConnection
            OutputStream outputStream = conn.getOutputStream();
            String aa = new String(param.getBytes("UTF8"),"UTF8");
            System.out.println("=========== "+aa);
            out = new PrintWriter(outputStream);
            // 發送請求參數
            out.print(aa);            
            out.flush();
            // 定義BufferedReader來讀取URL的回應
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("發送 POST 請求出現異常!"+e);            
        }        
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
	
}

package test;


import java.net.*;
import java.io.*;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class BaiduScrape {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String baseUrl = "https://baike.baidu.com/item/";
        String url = "";
        
        // 如果输入文字不是"exit"，则爬取其百度百科的介绍部分，否则退出该程序
        while(true) {
            System.out.println("输入相关英文进行搜索~输出exit退出:");
            url = input.nextLine();
            if(url.equals("exit")) {
                System.out.println("程序结束.");
                break;
            }
            String introduction = getContent(baseUrl+url);
            System.out.println(introduction+'\n');
        }
    }
    
    // getContent()函数主要实现爬取输入文字的百度百科的介绍部分
    public static String getContent(String url){
        // 利用URL解析网址
        URL urlObj = null;
        try{
            urlObj = new URL(url);
            
        }
        catch(MalformedURLException e){
            System.out.println("The url was malformed!");
            return "";
        }
        
        // URL连接
        URLConnection urlCon = null;
        try{
            urlCon = urlObj.openConnection(); // 打开URL连接
            // 将HTML内容解析成UTF-8格式
            Document doc = Jsoup.parse(urlCon.getInputStream(), "utf-8", url);
            // 刷选需要的网页内容
            String contentText = doc.select("div.lemma-summary").first().text();
            // 利用正则表达式去掉字符串中的"[数字]"
            contentText = contentText.replaceAll("\\[\\d+\\]", "");
            return contentText;
        }catch(IOException e){
            System.out.println("连接到URL时发生错误");
            return "";
        }
        
    }
}
package com.example.newstime.Adapter;

public class yiyunItem {

    private String yiyun_text;
    private  String yiyun_src;

    public yiyunItem(String yiyun_text,String yiyun_src){
        this.yiyun_text=yiyun_text;
        this.yiyun_src=yiyun_src;
    }

    public String getYiyun_text(){
        return  yiyun_text;
    }

    public String getYiyun_src(){
        return yiyun_src;
    }
}

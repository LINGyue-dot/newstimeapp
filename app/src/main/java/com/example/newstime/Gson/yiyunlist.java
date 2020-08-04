package com.example.newstime.Gson;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

public class yiyunlist extends DataSupport {

    @SerializedName("content")
    public String yiyunText;

    @SerializedName("source")
    public String yiyunSrc;


    public  yiyunlist(String yiyunText,String yiyunSrc){
        this.yiyunText=yiyunText;
        this.yiyunSrc=yiyunSrc;
    }

}

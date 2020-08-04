package com.example.newstime.Gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class yiyun  {

    @SerializedName("code")
    public int code;

    @SerializedName("msg")
    public   String msgs;



    @SerializedName("newslist")
    public  List<yiyunlist> yiyunList;

}

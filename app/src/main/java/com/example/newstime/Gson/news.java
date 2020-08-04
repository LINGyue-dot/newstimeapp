package com.example.newstime.Gson;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

import java.util.List;

public class news {


    @SerializedName("msg")
    public String msg;

    @SerializedName("newslist")
     public List<newslist> newslists;



}

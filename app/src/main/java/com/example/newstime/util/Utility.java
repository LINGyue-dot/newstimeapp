package com.example.newstime.util;

import android.text.TextUtils;
import android.widget.TextView;

import com.example.newstime.Gson.news;
import com.example.newstime.Gson.newslist;
import com.example.newstime.Gson.yiyun;
import com.example.newstime.Gson.yiyunlist;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.util.List;


public class Utility {


    //处理返回的新闻数据

    public static boolean handleNewsResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                news A = new Gson().fromJson(response, news.class);
                //存到数据库中
                DataSupport.deleteAll("newslist");//删除原数据库
                for (newslist B : A.newslists) {
                    newslist C = new newslist(B.ctime, B.titles, B.description, B.picUrl, B.url);
                    C.save();
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //处理返回亦云数据
    public static boolean handleYiyunResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                yiyun A = new Gson().fromJson(response, yiyun.class);
//                DataSupport.deleteAll("yiyunlist");//删除原来数据库
                for (yiyunlist B : A.yiyunList) {
                    yiyunlist C = new yiyunlist(B.yiyunText, B.yiyunSrc);
                    C.save();
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }




}

package com.example.newstime.util;

import android.text.TextUtils;
import android.widget.TextView;

import com.example.newstime.Gson.news;
import com.example.newstime.Gson.newslist;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.util.List;


public class Utility {


    //将返回的JSON格式转换为news的对象,static使其不局限于本文件
    public static news getNews(String response) {
        Gson gson = new Gson();
        return gson.fromJson(response, news.class);
    }

    //处理返回的数据

    public static boolean handleNewsResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                news A = new Gson().fromJson(response, news.class);
                //存到数据库中

                DataSupport.deleteAll("newslist");//删除原数据库
                for(newslist B: A.newslists ){
                    newslist C =new newslist(B.ctime,B.titles,B.description,B.picUrl,B.url);
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

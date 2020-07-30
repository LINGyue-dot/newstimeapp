package com.example.newstime.Gson;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

public class newslist extends DataSupport {

    @SerializedName("ctime")
    public String ctime;

    @SerializedName("title")
    public String titles;

    @SerializedName("description")
    public String description;

    @SerializedName("picUrl")
    public String picUrl;

    @SerializedName("url")
    public  String url;

    public  newslist(String ctime,String title,String description,String picUrl,String url){
        this.ctime=ctime;
        this.titles=title;
        this.description=description;
        this.picUrl=picUrl;
        this.url=url;
    }

    public void setTitle(String title){
        this.titles=title;
    }

    public String  getTitle(){
        return  titles;
    }

    public  void setUrl(String url){
        this.url=url;
    }

    public  String getUrl(){
        return url;
    }


}

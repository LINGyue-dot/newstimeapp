package com.example.newstime;

import org.antlr.v4.codegen.model.SrcOp;
import org.litepal.crud.DataSupport;

public class news_item {

    public String title;
    public String Date;
    public  String newsDescription;
    public String imgUrl;
    public String webUrl;

    public news_item(String title, String Date,String newsDescription, String imgUrl,String webUrl) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.Date = Date;
        this.newsDescription=newsDescription;
        this.webUrl=webUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getDate() {
        return Date;
    }

    public  String getNewsDescription(){
        return  newsDescription;
    }

    public String getWebUrl(){
        return webUrl;
    }



}

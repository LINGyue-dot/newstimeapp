package com.example.newstime.Mysqldb;

import java.io.Serializable;

public class UserCollection implements Serializable {

    private  String id;
    private String  yiyun;
    private  String src;

    public UserCollection() {

    }

    public String getId() {
        return id;
    }

    public String getYiyun() {
        return yiyun;
    }

    public String getSrc() {
        return src;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setYiyun(String yiyun) {
        this.yiyun = yiyun;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}

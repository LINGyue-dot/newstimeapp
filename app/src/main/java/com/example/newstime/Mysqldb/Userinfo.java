package com.example.newstime.Mysqldb;

import java.io.Serializable;

/*
* 用户信息实体类
* */

public class Userinfo implements Serializable {

    private  String id;  //id
    private  String upass; //密码


    public Userinfo(){}

    public Userinfo(String id, String upass) {
        this.id = id;
        this.upass = upass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }
}

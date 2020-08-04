package com.example.newstime.util;

import org.litepal.crud.DataSupport;

public class Account extends DataSupport {

    public    String ids;
    public String passwords;

    public Account (String ids,String passwords){
        this.ids=ids;
        this.passwords=passwords;
    }
    public Account(){}


    public String getIds() {
        return ids;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }
}

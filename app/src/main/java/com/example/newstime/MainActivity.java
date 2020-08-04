package com.example.newstime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView user_id;
    private TextView user_change;
    private TextView user_account;
    private TextView logo_out;
    public  boolean isShow =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //接收注册 传来的数据
        isShow = getIntent().getBooleanExtra("isShow", false);
        String id = getIntent().getStringExtra("id");


        //初始化
        user_id = findViewById(R.id.user_id);
        user_change = findViewById(R.id.user_change);
        user_account = findViewById(R.id.user_account);
        logo_out = findViewById(R.id.logo_out);

        if (isShow) {
            showView(id);
            //
            logo_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isShow=false;
                    initView();
                }
            });


        } else {
           initView();
        }

    }

    //已登入
    public  void showView(String id){
        user_id.setVisibility(View.GONE);
        user_account.setVisibility(View.VISIBLE);
        user_account.setText("Hello, " + id);
        user_change.setVisibility(View.VISIBLE);
        logo_out.setVisibility(View.VISIBLE);
    }
    //退出登入或者未登入
    public  void initView(){
        user_id.setVisibility(View.VISIBLE);
        user_account.setVisibility(View.GONE);
        user_account.setText("");
        user_change.setVisibility(View.GONE);
        logo_out.setVisibility(View.GONE);
    }



}

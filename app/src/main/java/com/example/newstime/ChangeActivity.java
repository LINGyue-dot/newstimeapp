package com.example.newstime;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newstime.Mysqldb.UserDao;
import com.example.newstime.Mysqldb.Userinfo;
import com.example.newstime.util.Account;
import com.example.newstime.util.ToastUtils;

import org.litepal.crud.DataSupport;

import java.util.List;

import static org.litepal.LitePalApplication.getContext;

public class ChangeActivity extends AppCompatActivity {

    private EditText change_id;
    private EditText change_password;
    private EditText change_passwords;
    private Button change_btn;
    private UserDao userDao; //用户操作数据库辅助类

    private Handler mainHandler;//主线程


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        //  初始化
        change_id = findViewById(R.id.change_id);
        change_password = findViewById(R.id.change_password);
        change_passwords = findViewById(R.id.change_passwords);
        change_btn = findViewById(R.id.change_btn);

        userDao = new UserDao();

        mainHandler = new Handler(getMainLooper());//主线程

        //
        change_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              doChange();
            }
        });
    }

    //修改密码
    public  void doChange(){
        String id =change_id.getText().toString().trim();
        String password =change_password.getText().toString().trim();
        String passwords =change_passwords.getText().toString().trim();
        if(TextUtils.isEmpty(password)||TextUtils.isEmpty(passwords)){
            showToast("请输入完整");
        }else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final  Userinfo item =userDao.getUserByIdAndUpass(id,password);

                            if(item==null){
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        showToast("账号或密码错误");
                                    }
                                    });
                            }else {
                                Userinfo A =new Userinfo(id,passwords);
                                userDao.editUser(A);
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        showToast("成功");
                                    }
                                });
                            }
                        }
            }).start();
        }
    }
    //Toast
    public void showToast(String A) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showToast(getContext(), A);
            }
        });
    }
}

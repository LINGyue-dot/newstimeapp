package com.example.newstime;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newstime.Mysqldb.UserDao;
import com.example.newstime.Mysqldb.Userinfo;
import com.example.newstime.util.Account;
import com.example.newstime.util.ToastUtils;

import org.litepal.crud.DataSupport;

import java.util.List;

import static org.litepal.LitePalApplication.getContext;

public class ResgisterActivity extends AppCompatActivity {

    private EditText id_register;
    private EditText password_register;
    private EditText password_registers;
    private Button button_register;


    private Handler mainHandler;//主线程
    private UserDao dao;// 用户数据库操作类


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //初始化
        id_register = findViewById(R.id.id_register);
        password_register = findViewById(R.id.password_register);
        password_registers = findViewById(R.id.password_registers);
        button_register = findViewById(R.id.button_register);

        dao = new UserDao();

        mainHandler = new Handler(getMainLooper());//获取主线程

        //注册按钮绑定事件
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }


    //注册
    private void register() {

        final String id = id_register.getText().toString().trim();
        final String password = password_register.getText().toString().trim();
        final String passwords = password_registers.getText().toString().trim();

        if (TextUtils.isEmpty(id) || TextUtils.isEmpty(password) || TextUtils.isEmpty(passwords)) {
            showToast("请输入完整");
        } else if (!password.equals(passwords)) {
            showToast("两次密码输入不同");
        } else {
            final Userinfo item = new Userinfo(id, password);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final List<Userinfo> list = dao.getAllUserList();
                    boolean isLive = false;
                    for (Userinfo A : list) {
                        if (A.getId().equals(id)) {
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    showToast("该用户名已存在");
                                }
                            });
                            isLive = true;
                            break;
                        }
                    }
                    if (!isLive) {
                        int iRow = dao.addUser(item);
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                showToast("注册成功");
                                Intent intent = new Intent(ResgisterActivity.this, MainActivity.class);
                                intent.putExtra("isShow", true);
                                intent.putExtra("id", id);
                                startActivity(intent);
                            }
                        });

                    }

//                Wrong  code;
//                    mainHandler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            boolean isLive = false;
//                            for (Userinfo A : list) {
//                                if (A.getId().equals(id)) {
//                                    showToast("该用户名已存在");
//                                    isLive = true;
//                                    break;
//                                }
//                            }
//                            if (!isLive) {
//                                final Userinfo B = new Userinfo();
//                                B.setId(id);
//                                B.setUpass(password);
//                                final int iRow =dao.addUser(B);
//                                showToast("注册成功");
//                                Intent intent = new Intent(ResgisterActivity.this, MainActivity.class);
//                                intent.putExtra("isShow", true);
//                                intent.putExtra("id", id);
//                                startActivity(intent);
//                            }
//                        }
//                    });
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



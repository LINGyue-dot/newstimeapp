package com.example.newstime;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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


public class LogoinActivity extends AppCompatActivity {

    private EditText logoin_id;
    private EditText logoin_password;
    private  Button  logoin_btn;
    private TextView register;

    private Handler mainHandler;//主线程
    private UserDao dao;//用户数据库操作类


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //初始化
        logoin_id=findViewById(R.id.logoin_id);
        logoin_password=findViewById(R.id.logoin_password);
        logoin_btn=findViewById(R.id.logoin_btn);
        register=findViewById(R.id.register);

        mainHandler=new Handler(getMainLooper());//获取主线程
        dao=new UserDao();

        //添加绑定事件
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LogoinActivity.this,ResgisterActivity.class);
                startActivity(intent);
            }
        });

        logoin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dologoin();
            }
        });

    }





    //登入操作
    private  void dologoin(){

        final String id = logoin_id.getText().toString().trim();
        final String password= logoin_password.getText().toString().trim();

        if(TextUtils.isEmpty(id)){
            showToast("输入用户名");
            logoin_id.requestFocus();

        }else if(TextUtils.isEmpty(password)){
            showToast("请输入密码");
            logoin_password.requestFocus();
        }else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final Userinfo item =dao.getUserByIdAndUpass(id,password);
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(item==null){
                                showToast("用户密码错误");
                            }else {
                                showToast("登入成功   Hello"+id);
                                Intent intent =new Intent(LogoinActivity.this,MainActivity.class);
                                intent.putExtra("isShow",true);
                                intent.putExtra("id",id);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }).start();
        }




    }




















    //Toast显示
    public void showToast(String A){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showToast(getContext(),A);
            }
        });
    }

}

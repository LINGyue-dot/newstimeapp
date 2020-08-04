package com.example.newstime;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //初始化
        logoin_id=findViewById(R.id.logoin_id);
        logoin_password=findViewById(R.id.logoin_password);
        logoin_btn=findViewById(R.id.logoin_btn);
        register=findViewById(R.id.register);

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
                String id =logoin_id.getText().toString();
                String password= logoin_password.getText().toString();
                boolean isLive =false;//判断账号是否存在
                if(!id.equals("")&&!password.equals("")){
                    List<Account> A = DataSupport.findAll(Account.class);
                    for(Account B :A ){
                        if(id.equals(B.ids)){
                            isLive=true;

                            if (!password.equals(B.passwords)){
                                showToast("密码错误");
                                logoin_id.setText("");
                                logoin_password.setText("");
                            }else {
                                Intent intent = new Intent(LogoinActivity.this,MainActivity.class);
                                intent.putExtra("isShow",true);
                                intent.putExtra("id",id);
                                startActivity(intent);

                            }


                        }
                    }

                    if (!isLive)
                    showToast("账号不存在");

                }else {
                    showToast("请输入完整");
                }




            }
        });

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

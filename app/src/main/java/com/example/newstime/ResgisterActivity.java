package com.example.newstime;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newstime.util.Account;
import com.example.newstime.util.ToastUtils;

import org.litepal.crud.DataSupport;

import java.util.List;

import static org.litepal.LitePalApplication.getContext;

public class ResgisterActivity extends AppCompatActivity {

    private EditText  id_register ;
    private  EditText password_register;
    private  EditText password_registers;
    private Button  button_register;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //初始化
        id_register =findViewById(R.id.id_register);
        password_register=findViewById(R.id.password_register);
        password_registers=findViewById(R.id.password_registers);
        button_register =findViewById(R.id.button_register);

        //注册按钮绑定事件
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isLive =false;
                String id_register_s= id_register.getText().toString();
                String password_register_s= password_register.getText().toString();
                String password_registers_s=  password_registers.getText().toString();
                if((!id_register_s.equals("")) && (!password_register_s.equals(""))
                &&(!password_registers_s.equals(""))){
                    //遍历数据库
                    List<Account> A = DataSupport.findAll(Account.class);
                    for(Account B :A){
                        if(B.ids.equals(id_register_s) ){
                            showToast("该用户名已被注册");
                            isLive= true;
                            break;
                        }
                    }
                    //如果不存该id
                    if(!isLive){
                        if(!password_register_s.equals(password_registers_s))
                        {
                            showToast("两次密码不一致");
                        }else {
                            //存入本地数据库中
                            Account E = new Account(id_register_s,password_register_s);
                            E.save();
                            showToast("注册成功");
                            Intent intent =new Intent(ResgisterActivity.this,MainActivity.class);
                            intent.putExtra("isShow",true);
                            intent.putExtra("id",id_register_s);
                            startActivity(intent);
                        }
                    }




                }
                else {
                    showToast("请输入完整");
                }
            }
        });

    }





    //Toast
    public  void showToast(String A){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showToast(getContext(),A);
            }
        });
    }
}

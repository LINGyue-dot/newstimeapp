package com.example.newstime;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newstime.util.Account;
import com.example.newstime.util.ToastUtils;

import org.litepal.crud.DataSupport;

import java.util.List;

import static org.litepal.LitePalApplication.getContext;

public class ChangeActivity extends AppCompatActivity {

    private EditText change_id;
    private EditText change_password;
    private EditText change_passwords;
    private Button  change_btn;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);



        //  初始化
        change_id=findViewById(R.id.change_id);
        change_password=findViewById(R.id.change_password);
        change_passwords=findViewById(R.id.change_passwords);
        change_btn=findViewById(R.id.change_btn);

        //
        change_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id =change_id.getText().toString();
                String  op =change_password.getText().toString();
                String ed =change_passwords.getText().toString();
                boolean isLive =false;
                if(!id.equals("")&&!op.equals("")&&!ed.equals("")){
                    List<Account> A = DataSupport.findAll(Account.class);
                    for(Account B : A) {
                        if(id.equals(B.ids)){
                            isLive=true;

                            if(op.equals(B.passwords)){
                                Account C =new Account();
                                C.setPasswords(ed);
                                C.updateAll("ids = ?",id);
                                showToast("修改成功");
                                Intent intent =new Intent(ChangeActivity.this,MainActivity.class);
                                startActivity(intent);
                            }else {
                                showToast("原密码错误修改失败");
                                change_password.setText("");
                            }
                            break;
                        }

                        if (!isLive){
                            showToast("不存在此账号");
                        }
                    }

                }else {
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

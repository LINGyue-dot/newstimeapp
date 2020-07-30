package com.example.newstime;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WebActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        //接收 ChooseNewsFragment的数据
        String url =getIntent().getStringExtra("webUrl");

        WebView web =findViewById(R.id.web);
        web.loadUrl(url);

        //将webView界面不在浏览器中打开直接在app中打开
        web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view,String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}

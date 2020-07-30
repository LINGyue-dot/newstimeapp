package com.example.newstime;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.newstime.Gson.newslist;
import com.example.newstime.util.HttpUtil;
import com.example.newstime.util.Utility;

import org.jetbrains.annotations.NotNull;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ChooseNewsFragment extends Fragment {


    private Button nav_button;
    private TextView nav_title;
    private List<news_item> newsItemList=new ArrayList<>();
    private List<newslist> listNews;
    private ListView listView;
    private TextView newsTitle;
    private ImageView newsImage;
    private newsAdapter adapter;




    //初始化
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.activity_news_fragment,container,false);
        nav_title=view.findViewById(R.id.nav_title);
        nav_button=view.findViewById(R.id.nav_btn);
        listView=view.findViewById(R.id.list_view);
        adapter = new newsAdapter(getContext(),R.layout.news_item,newsItemList);//适配器
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getResponse();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =new Intent(getActivity(), WebActivity.class);
                //传输数据给web activity
                intent.putExtra("webUrl",newsItemList.get(i).getWebUrl());
                startActivity(intent);

                //


            }
        });


    }


















//发起请求并展示新闻
    public void getResponse(){

        final String address="http://api.tianapi.com/social/index?key=aca9747e9baad0a60d161b4f744739ae&num=15";
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String responseText =response.body().string();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Utility.handleNewsResponse(responseText);//存入数据库中
                        queryNews();
                    }
                });
            }
        });

    }


    //查询数据库中新闻
    private  void queryNews(){
        nav_button.setVisibility(View.GONE);
        listNews = DataSupport.findAll(newslist.class);
        if(listNews.size()>0){
            newsItemList.clear();
            for(newslist A:listNews){
                news_item B =new news_item(A.titles,A.ctime,A.description,A.picUrl,A.url);
                newsItemList.add(B);
            }
            adapter.notifyDataSetChanged();//动态更新
            listView.setSelection(0);
        }
    }




}

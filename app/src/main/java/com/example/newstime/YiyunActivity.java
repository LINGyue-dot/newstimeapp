package com.example.newstime;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newstime.Adapter.yiyunAdapter;
import com.example.newstime.Adapter.yiyunItem;
import com.example.newstime.Gson.yiyun;
import com.example.newstime.Gson.yiyunlist;
import com.example.newstime.Mysqldb.CollectionDao;
import com.example.newstime.Mysqldb.UserCollection;
import com.example.newstime.Mysqldb.UserDao;
import com.example.newstime.util.HttpUtil;
import com.example.newstime.util.ToastUtils;
import com.example.newstime.util.Utility;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static org.litepal.LitePalApplication.getContext;

public class YiyunActivity extends AppCompatActivity {

    private List<yiyunItem> yiyunList = new ArrayList<>();
    private TextView yiyunText;
    private TextView yiyunSrc;
    private yiyunAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;

    private Handler mainHandler;//主线程
    private CollectionDao dao;//用户数据库操作类


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yiyun);

        //
        String id = getIntent().getStringExtra("id");//接收main传来的id

        //
        swipeRefresh = findViewById(R.id.swipe_freshs);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);//设置下拉进度条颜色
        for (int i = 0; i <= 20; i++)//一次加载20条
            getYiyunResponse();
        //初始化

        yiyunAdapter.x=0;//用于判断activity 是收藏还是亦云
        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new yiyunAdapter(yiyunList);
        recyclerView.setAdapter(adapter);

        dao =new CollectionDao() ;
        mainHandler = new Handler(getMainLooper());//获取主线程

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.addOnScrollListener(monScrollListener);
        adapter.notifyDataSetChanged();//


        //
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                for (int i = 0; i <= 20; i++)
                    getYiyunResponse();
            }
        });

        //收藏按钮的操作
        adapter.setOnItemClickListener(new yiyunAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, yiyunAdapter.ViewName viewName, int position) {
                switch (v.getId()){
                    case R.id.yiyun_out:
                        adapter.notifyDataSetChanged();
                        if(id.isEmpty()){
                            showToast("未登入");
                        }else {
                            //添加
                            addCollection(id, yiyunList.get(position).getYiyun_text(),
                                    yiyunList.get(position).getYiyun_src());
                        }
                        break;
                    case R.id.yiyun_in:
                        adapter.notifyDataSetChanged();
                        deleteCollection(id, yiyunList.get(position).getYiyun_text(),
                                yiyunList.get(position).getYiyun_src());
                        break;
                }
            }

            @Override
            public void onItemLongClick(View v) {

            }
        });


    }

    //发起亦云请求
    public void getYiyunResponse() {

        final String address = "http://api.tianapi.com/txapi/hotreview/index?key=aca9747e9baad0a60d161b4f744739ae";

        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String yiyunResponse = response.body().string();
                Utility.handleYiyunResponse(yiyunResponse);//存入数据库
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        queryYiyun();
                        swipeRefresh.setRefreshing(false);//刷新事件结束隐藏进度条
                    }
                });
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

                Toast.makeText(YiyunActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void queryYiyun() {
        List<yiyunlist> list = DataSupport.findAll(yiyunlist.class);
        if (list.size() > 0) {
            yiyunList.clear();
            for (yiyunlist A : list) {
                yiyunItem B = new yiyunItem(A.yiyunText, A.yiyunSrc);
                yiyunList.add(B);
            }
            adapter.notifyDataSetChanged();//动态更新
        }
    }


    //监听recycle是否 到底部
    private int mLastVisibleItemPosition;
    private RecyclerView.OnScrollListener monScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                mLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            }
            if (adapter != null) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && mLastVisibleItemPosition + 1 == adapter.getItemCount()) {
                    //发送网络请求获取更多数据
                    for (int i = 1; i <= 5; i++)
                        getYiyunResponse();
                }
            }
        }
    };



    //加入服务器上的数据库中
    private  void addCollection(String id,String text,String src){

        final UserCollection item =new UserCollection(id,text,src);
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.addCollection(item);
                showToast("加入收藏成功");
            }
        }).start();
    }

    //删除服务器上的数据库中的该短句
    private  void deleteCollection(String id,String text,String src){
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.delUserCollection(id,text);
                showToast("取消收藏成功");
            }
        }).start();
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

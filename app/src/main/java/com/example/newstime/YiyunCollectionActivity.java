package com.example.newstime;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newstime.Adapter.yiyunAdapter;
import com.example.newstime.Adapter.yiyunItem;
import com.example.newstime.Gson.yiyunlist;
import com.example.newstime.Mysqldb.CollectionDao;
import com.example.newstime.Mysqldb.UserCollection;
import com.example.newstime.util.ToastUtils;

import org.stringtemplate.v4.ST;

import java.util.ArrayList;
import java.util.List;

import static org.litepal.LitePalApplication.getContext;

public class YiyunCollectionActivity extends AppCompatActivity  {

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
        setContentView(R.layout.activity_collection);


        //
        String id = getIntent().getStringExtra("id");//接收main传来的id



        //
        swipeRefresh = findViewById(R.id.swipe_freshs);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);//设置下拉进度条颜色
        //获取远程数据库中的数据并且放入yiyunList

        dao = new CollectionDao();
        mainHandler = new Handler(getMainLooper());//获取主线程

        //recyclelist初始化
        yiyunAdapter.x=1;//用于判断activity 是收藏还是亦云
        InitCollection(id);
        RecyclerView recyclerView = findViewById(R.id.recycle_views);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new yiyunAdapter(yiyunList);
        recyclerView.setAdapter(adapter);


        //收藏按钮的操作
        adapter.setOnItemClickListener(new yiyunAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, yiyunAdapter.ViewName viewName, int position) {
                switch (v.getId()) {
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

        //swiperfresh
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                InitCollection(id);
                adapter.notifyDataSetChanged();
            }
        });


    }


    //获取远程数据库中的数据并且放入yiyunList
    private void InitCollection(String id) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<UserCollection> list = dao.getById(id);

                if (yiyunList.size() > 0)
                    yiyunList.clear();
                for (UserCollection B : list) {
                    yiyunItem C = new yiyunItem(B.getYiyun(), B.getSrc());
                    System.out.println(B.getYiyun());
                    yiyunList.add(C);
                }
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        showToast("成功");
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);//刷新事件结束隐藏进度条
                    }
                });

            }
        }).start();

    }



    //删除服务器上的数据库中的该短句
    private void deleteCollection(String id, String text, String src) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.delUserCollection(id, text);
                showToast("取消收藏成功");
            }
        }).start();
    }

    //Toast显示
    public void showToast(String A) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showToast(getContext(), A);
            }
        });
    }
}

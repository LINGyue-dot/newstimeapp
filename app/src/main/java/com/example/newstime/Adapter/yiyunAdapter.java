package com.example.newstime.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.newstime.R;

import java.util.List;

public class yiyunAdapter extends RecyclerView.Adapter<yiyunAdapter.ViewHolder> {

    private List<yiyunItem> yiyunList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView yiyunText;
        TextView yiyunSrc;

        public ViewHolder(View view) {
            super(view);
            yiyunText =  view.findViewById(R.id.yiyun_text);
            yiyunSrc =view.findViewById(R.id.yiyun_src);
        }

    }

    public yiyunAdapter(List<yiyunItem> yiyunList) {
        this.yiyunList = yiyunList;
    }


    //创建ViewHolder实例,并把加载的布局传入到构造函数去,再把ViewHolder实例返回。
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.yiyun_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //对子项的数据进行赋值,会在每个子项被滚动到屏幕内时执行。
    //
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        yiyunItem yiyun = yiyunList.get(position);
        holder.yiyunText.setText(yiyun.getYiyun_text());
        holder.yiyunSrc.setText("  ---   "+yiyun.getYiyun_src());
    }
    //返回RecyclerView的子项数目。
    @Override
    public int getItemCount() {
        return yiyunList.size();
    }



}

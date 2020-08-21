package com.example.newstime.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.newstime.R;

import java.util.List;

public class yiyunAdapter extends RecyclerView.Adapter<yiyunAdapter.ViewHolder>
        implements View.OnClickListener {

    private List<yiyunItem> yiyunList;
    public  static  int  x;// 用于判断activity 是收藏还是亦云


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView yiyunText;
        TextView yiyunSrc;
        Button yiyunOut;
        Button yiyunIn;

        public ViewHolder(View view) {
            super(view);
            yiyunText = view.findViewById(R.id.yiyun_text);
            yiyunSrc = view.findViewById(R.id.yiyun_src);

            //
            yiyunOut = view.findViewById(R.id.yiyun_out);
            yiyunIn = view.findViewById(R.id.yiyun_in);

//            添加点击事件
            view.setOnClickListener(yiyunAdapter.this);
            yiyunIn.setOnClickListener(yiyunAdapter.this);
            yiyunOut.setOnClickListener(yiyunAdapter.this);
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
        holder.yiyunSrc.setText("  ---   " + yiyun.getYiyun_src());

        //绑定
        holder.yiyunOut.setTag(position);

        holder.yiyunIn.setTag(position);


        //判断是否显示已收藏按钮
        if(yiyunList.get(position).getIsCollection()){
            holder.yiyunOut.setVisibility(View.GONE);
            holder.yiyunIn.setVisibility(View.VISIBLE);
        }else {
            holder.yiyunOut.setVisibility(View.VISIBLE);
            holder.yiyunIn.setVisibility(View.GONE);
        }

        if(x==1){
            holder.yiyunOut.setVisibility(View.GONE);
            holder.yiyunIn.setVisibility(View.VISIBLE);
        }


    }

    //返回RecyclerView的子项数目。
    @Override
    public int getItemCount() {
        return yiyunList.size();
    }


    //收藏按钮事件
    //item里面有多个控件可以点击
    public enum ViewName {
        ITEM,
        PRACTISE
    }

    //自定义一个回调接口来实现Click和LongClick事件
    public interface OnItemClickListener {
        void onItemClick(View v, ViewName viewName, int position);

        void onItemLongClick(View v);
    }

    private OnItemClickListener mOnItemClickListener;//声明自定义的接口

    //定义方法并暴露给外面的调用者
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    @Override
    public void onClick(View view) {

        int position = (int) view.getTag(); //getTag()获取数据
        if (mOnItemClickListener != null) {
            switch (view.getId()) {
                case R.id.yiyun_in:
                    mOnItemClickListener.onItemClick(view, ViewName.PRACTISE, position);

                    break;
                case R.id.yiyun_out:
                    mOnItemClickListener.onItemClick(view, ViewName.ITEM, position);
                    break;
            }
        }
    }
}

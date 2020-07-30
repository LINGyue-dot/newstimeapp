package com.example.newstime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.bumptech.glide.Glide;
import java.util.List;

public class newsAdapter extends ArrayAdapter<news_item> {


    private int resouceId;


    //适配器
    public newsAdapter(@NonNull Context context, int textViewResouceId, List<news_item> objects) {
        super(context, textViewResouceId, objects);
        resouceId = textViewResouceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        news_item newsObject = getItem(position);//获取当前news 的实例

        View view = LayoutInflater.from(getContext()).inflate(resouceId, parent, false);
        ImageView newsImage = view.findViewById(R.id.news_image);
        TextView newsTitle = view.findViewById(R.id.news_title);
        TextView newsDate =view.findViewById(R.id.news_date);
        TextView newsDescription =view.findViewById(R.id.news_description);
        newsTitle.setText(newsObject.getTitle());
        newsDate.setText(newsObject.getDate());
        newsDescription.setText(newsObject.getNewsDescription());
        Glide.with(getContext()).load(newsObject.getImgUrl()).into(newsImage);//用glide加载图片
        return view;

    }


}

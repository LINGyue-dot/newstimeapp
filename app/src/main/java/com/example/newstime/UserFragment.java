package com.example.newstime;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UserFragment extends Fragment {

    private ImageView userImage;
    private TextView userId;
    private TextView userSet;
    private TextView userChange;
    private TextView yiyun;
    private TextView user_account;
    private  TextView yiyun_collection;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view =inflater.inflate(R.layout.activity_user,container,false);

        //
        userImage =view.findViewById(R.id.user_image);
        userId = view.findViewById(R.id.user_id);
        userSet =view.findViewById(R.id.user_set);
        userChange =view.findViewById(R.id.user_change);
        yiyun =view.findViewById(R.id.yiyun_news);
        user_account=view.findViewById(R.id.user_account);
        yiyun_collection=view.findViewById(R.id.collection);

        return view;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        //登入
        userId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),LogoinActivity.class);
                startActivity(intent);
            }
        });


        //yiyun
        yiyun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(), YiyunActivity.class);
                intent.putExtra("id",user_account.getText().toString().trim());
                startActivity(intent);
            }
        });


        //修改密码
        userChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),ChangeActivity.class);
                intent.putExtra( "id",user_account.getText().toString().trim());
                startActivity(intent);
            }
        });

        //收藏
        yiyun_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),YiyunCollectionActivity.class);
                intent.putExtra("id",user_account.getText().toString().trim());
                startActivity(intent);
            }
        });





    }
}

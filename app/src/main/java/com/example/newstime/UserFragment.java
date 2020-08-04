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



        return view;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        userId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),LogoinActivity.class);
                startActivity(intent);
            }
        });

        yiyun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(), YiyunActivity.class);
                startActivity(intent);
            }
        });

        userChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),ChangeActivity.class);
                startActivity(intent);
            }
        });





    }
}

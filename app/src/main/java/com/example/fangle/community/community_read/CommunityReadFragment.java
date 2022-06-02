package com.example.fangle.community.community_read;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.fangle.R;
import com.example.fangle.bulletinboard.bulletinboard_read.BulletinboardReadActivity;
import com.example.fangle.join.JoinActivity;

public class CommunityReadFragment extends Fragment {

    TextView artist_name01;
    ImageButton imageButton01;
    Button join01;

    String artist_name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_community_read, container, false);

        join01 = (Button)v.findViewById(R.id.join01); // 조인 입니다.
        artist_name01 = (TextView)v.findViewById(R.id.artist_name01);
        imageButton01 = (ImageButton)v.findViewById(R.id.imageButton01);

        //커뮤니티 가입하기
        join01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                artist_name = artist_name01.getText().toString();
                Intent join_intent = new Intent(getActivity(), JoinActivity.class);
                join_intent.putExtra("artist_name",artist_name);
                startActivity(join_intent);
            }
        });

        //게시판 들어가기 . (게시판 연결)
        imageButton01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                artist_name = artist_name01.getText().toString();
                Intent borard_intent = new Intent(getActivity(), BulletinboardReadActivity.class);
                borard_intent.putExtra("artist_name",artist_name);
                startActivity(borard_intent);
            }
        });

        return v;
    }

}

package com.example.fangle.community.community_read;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.io.ByteArrayOutputStream;

public class CommunityReadFragment extends Fragment {

    TextView artist_name01,artist_name02,artist_name03,artist_name04,artist_name05,artist_name06,artist_name07,artist_name08,artist_name09;
    ImageButton imageButton01,imageButton02,imageButton03,imageButton04,imageButton05,imageButton06,imageButton07,imageButton08,imageButton09;
    Button join01,join02,join03,join04,join05,join06,join07,join08,join09;

    String artist_name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_community_read, container, false);

        join01 = (Button)v.findViewById(R.id.join01); // 조인 입니다.
        artist_name01 = (TextView)v.findViewById(R.id.artist_name01);
        imageButton01 = (ImageButton)v.findViewById(R.id.imageButton01);

        join02 = (Button)v.findViewById(R.id.join02); // 조인 입니다.
        artist_name02 = (TextView)v.findViewById(R.id.artist_name02);
        imageButton02 = (ImageButton)v.findViewById(R.id.imageButton02);

        join03 = (Button)v.findViewById(R.id.join03); // 조인 입니다.
        artist_name03 = (TextView)v.findViewById(R.id.artist_name03);
        imageButton03 = (ImageButton)v.findViewById(R.id.imageButton03);

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

                //이미지 보내기
                Bitmap sendBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.nmixx);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                sendBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                borard_intent.putExtra("image",byteArray);

                startActivity(borard_intent);
            }
        });

        //커뮤니티 가입하기
        join02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                artist_name = artist_name02.getText().toString();
                Intent join_intent = new Intent(getActivity(), JoinActivity.class);
                join_intent.putExtra("artist_name",artist_name);
                startActivity(join_intent);
            }
        });

        //게시판 들어가기 . (게시판 연결)
        imageButton02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                artist_name = artist_name02.getText().toString();
                Intent borard_intent = new Intent(getActivity(), BulletinboardReadActivity.class);
                borard_intent.putExtra("artist_name",artist_name);

                //이미지 보내기
                Bitmap sendBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.stray_kids);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                sendBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                borard_intent.putExtra("image",byteArray);

                startActivity(borard_intent);
            }
        });
        //커뮤니티 가입하기
        join03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                artist_name = artist_name03.getText().toString();
                Intent join_intent = new Intent(getActivity(), JoinActivity.class);
                join_intent.putExtra("artist_name",artist_name);
                startActivity(join_intent);
            }
        });

        //게시판 들어가기 . (게시판 연결)
        imageButton03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                artist_name = artist_name03.getText().toString();
                Intent borard_intent = new Intent(getActivity(), BulletinboardReadActivity.class);
                borard_intent.putExtra("artist_name",artist_name);

                //이미지 보내기
                Bitmap sendBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.itzy);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                sendBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                borard_intent.putExtra("image",byteArray);
                startActivity(borard_intent);
            }
        });

        return v;
    }

}

package com.example.fangle.profile.profile_read;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fangle.MainActivity;
import com.example.fangle.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileReadActivity extends AppCompatActivity {
    // 레이아웃 선언
    Button Log_out_button,nft_View_more_button,member_secession_button,member_modify_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_read);

        Log_out_button = (Button) findViewById(R.id.Log_out_button);
        nft_View_more_button = (Button) findViewById(R.id.nft_View_more_button);
        member_secession_button = (Button) findViewById(R.id.member_secession_button);
        member_modify_button = (Button) findViewById(R.id.member_modify_button);
        
        // 로그아웃 기능 구현
        Log_out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // nft 더보기 기능구현
        nft_View_more_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // nft 컬렉션으로 연결 시켜라
            }
        });

        // 회원 탈퇴 기능 구현
        member_secession_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // 회원 수정 기능 구현
        member_modify_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    // 개인 정보처리방침
    public void onClickPrivacyPolicy(View view){

    }
}
package com.example.fangle.profile.profile_read;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.fangle.R;
import com.example.fangle.main.main_read.MainReadActivity;

public class ProfileReadFragment extends Fragment {

    // 레이아웃 선언
    Button Log_out_button,nft_View_more_button,member_secession_button,member_modify_button;
    MainReadActivity MainReadActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile_read, container, false);

        Log_out_button = (Button) v.findViewById(R.id.Log_out_button);
        nft_View_more_button = (Button) v.findViewById(R.id.nft_View_more_button);
        member_secession_button = (Button) v.findViewById(R.id.member_secession_button);
        member_modify_button = (Button) v.findViewById(R.id.member_modify_button);

        // 로그아웃 기능 구현
        Log_out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ProfileReadActivity.class);
                startActivity(intent);
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

        return v;
    }

    // 개인 정보처리방침
    public void onClickPrivacyPolicy(View view){

    }

}
package com.example.fangle.intro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fangle.R;  // R 클래스 추가
import com.example.fangle.account.sign_in.SignInActivity;
import com.example.fangle.account.sign_up.SignUpActivity;


public class IntroActivity extends AppCompatActivity{

    // 버튼 선언
    Button signInButton, signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        signInButton = (Button) findViewById(R.id.sign_in);
        signUpButton = (Button) findViewById(R.id.sign_up);

        // 로그인 버튼
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_sign_in = new Intent(IntroActivity.this, SignInActivity.class);
                startActivity(intent_sign_in);
            }
        });

        // 회원가입 버튼
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_sign_up = new Intent(IntroActivity.this, SignUpActivity.class);
                startActivity(intent_sign_up);
            }
        });

    }

}
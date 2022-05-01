package com.example.fangle.account.sign_in;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fangle.R;

public class SignInActivity extends AppCompatActivity {

    EditText sign_in_id,sign_in_password;
    Button sign_in_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        sign_in_id = (EditText) findViewById(R.id.sign_in_id);
        sign_in_password = (EditText) findViewById(R.id.sign_in_password);

        sign_in_button = (Button) findViewById(R.id.sign_in_button);

        sign_in_button.setOnClickListener(new View.OnClickListener() {
            // 회원정보 가 맞는지 확인하고 맞으면 메인화면 아니면 정보를 다시 확인 하라는 에러 표시
            @Override
            public void onClick(View view) {

            }
        });
    }
}
package com.example.fangle.account.sign_in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fangle.MainActivity;
import com.example.fangle.R;
import com.example.fangle.account.sign_up.SignUpActivity;
import com.example.fangle.intro.IntroActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignInActivity extends AppCompatActivity {

    // 파이어베이스 로그인 관련 선언
    private FirebaseAuth firebaseAuth;

    // 레이아웃 관련 선언
    EditText sign_in_id,sign_in_password;
    Button sign_in_button;

    // 사용자가 입력한 로그인 정보 값 선언
    String id,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        sign_in_id = (EditText) findViewById(R.id.sign_in_id);
        sign_in_password = (EditText) findViewById(R.id.sign_in_password);

        sign_in_button = (Button) findViewById(R.id.sign_in_button);

        // 파이어베이스 로그인
        firebaseAuth = FirebaseAuth.getInstance();

        sign_in_button.setOnClickListener(new View.OnClickListener() {
            // 회원정보 가 맞는지 확인하고 맞으면 메인화면 아니면 정보를 다시 확인 하라는 에러 표시
            @Override
            public void onClick(View v) {
                get_sign_in_data();
                sign_in();
            }
        });
    }

    // 사용자가 입력한 로그인정보를 받는다.
    public void get_sign_in_data(){
        id = sign_in_id.getText().toString().trim();
        password = sign_in_password.getText().toString().trim();
    }

    // 파이어베이스 로그인
    private void sign_in(){
        firebaseAuth.signInWithEmailAndPassword(id,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(SignInActivity.this,"로그인 오류",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
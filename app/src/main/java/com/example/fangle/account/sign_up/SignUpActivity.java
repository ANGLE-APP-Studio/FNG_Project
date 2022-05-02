package com.example.fangle.account.sign_up;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.fangle.R;
import com.example.fangle.intro.IntroActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Date;



public class SignUpActivity extends AppCompatActivity {
    // 생년월일 입력관련 선언
    SimpleDateFormat mFormat_year = new SimpleDateFormat("yyyy");
    SimpleDateFormat mFormat_month = new SimpleDateFormat("MM");
    SimpleDateFormat mFormat_day = new SimpleDateFormat("dd");

    Date date = new Date();
    private int y=0, m=0, d=0;

    private int year = Integer.parseInt(mFormat_year.format(date));
    private int month = Integer.parseInt(mFormat_month.format(date));
    private int day = Integer.parseInt(mFormat_day.format(date));

    // 파이어베이스 회원가입 관련 선언
    private static final String TAG = "SignUpActivity";
    private FirebaseAuth mAuth;

    // 레이아웃 관련 선언
    EditText sign_up_id,sign_up_password,sign_up_email,sign_up_phone_number,sign_up_nickname,sign_up_birthdate,sign_up_gender;
    Button birthdate_button,sign_up_button;

    // 사용자가 입력한 값 관련 선언
    public String id,password,email,phone_number,nickname,birthdate,gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // 파이어베이스 mAuth
        mAuth = FirebaseAuth.getInstance();

        sign_up_id = (EditText) findViewById(R.id.sign_up_id);
        sign_up_password = (EditText) findViewById(R.id.sign_up_password);
        sign_up_email = (EditText) findViewById(R.id.sign_up_email);
        sign_up_phone_number = (EditText) findViewById(R.id.sign_up_phone_number);
        sign_up_nickname = (EditText) findViewById(R.id.sign_up_nickname);
        sign_up_birthdate = (EditText) findViewById(R.id.sign_up_birthdate);
        sign_up_gender = (EditText) findViewById(R.id.sign_up_gender);

        birthdate_button = (Button) findViewById(R.id.birthdate_button);
        sign_up_button = (Button) findViewById(R.id.sign_up_button);

        birthdate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimePicker(sign_up_birthdate); // 버튼을 눌르면 날짜 설정 다이얼로그가 나온다.
            }
        });

        sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                get_data();
                sign_Up();
            }
        });

    }

    // 입력한 회원정보 값을 저장한다.
    // 이 값을 데이터 베이스로 넘겨야함
    public void get_data(){
        id = sign_up_id.getText().toString(); // 사용자 아이디
        password = sign_up_password.getText().toString(); // 사용자 페스워드
        email = sign_up_email.getText().toString(); // 사용자 이메일
        phone_number = sign_up_phone_number.getText().toString(); // 사용자 전화번호
        nickname = sign_up_nickname.getText().toString(); // 사용자 닉네임
        birthdate = sign_up_birthdate.getText().toString(); //  사용자 생년월일
        gender = sign_up_gender.getText().toString(); // 사용자 성별
    }

    // When initializing your Activity, check to see if the user is currently signed in.
    // 활동을 초기화할 때 사용자가 현재 로그인되어 있는지 확인하십시오.
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        //사용자가 로그인했는지(null이 아님) 확인하고 그에 따라 UI를 업데이트합니다.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    // 파이어베이스 회원가입
    private void sign_Up(){
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    // Sign in success, update UI with the signed-in user's information
                    // 로그인 성공, 로그인한 사용자 정보로 UI 업데이트
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    Intent intent = new Intent(SignUpActivity.this, IntroActivity.class);
                    startActivity(intent);
                    Toast.makeText(SignUpActivity.this, "등록 완료", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    // If sign in fails, display a message to the user.
                    // 로그인에 실패하면 사용자에게 메시지를 표시합니다.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(SignUpActivity.this, "등록 에러", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

    //생년월일 설정
    private void showDateTimePicker(TextView date) {

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                y = year;
                m = month+1;
                d = dayOfMonth;
                date.setText(String.format("%d년-%d월-%d일", y,m,d));
            }

        },year, month-1, day);

        datePickerDialog.setMessage("생년월일");
        datePickerDialog.show();
    }
}
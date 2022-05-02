package com.example.fangle.account.sign_up;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.fangle.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUpActivity extends AppCompatActivity {
    //생년월일 입력관련 선언
    SimpleDateFormat mFormat_year = new SimpleDateFormat("yyyy");
    SimpleDateFormat mFormat_month = new SimpleDateFormat("MM");
    SimpleDateFormat mFormat_day = new SimpleDateFormat("dd");

    Date date = new Date();
    private int y=0, m=0, d=0;

    private int year = Integer.parseInt(mFormat_year.format(date));
    private int month = Integer.parseInt(mFormat_month.format(date));
    private int day = Integer.parseInt(mFormat_day.format(date));
    
    // 레이아웃 관련 선언
    EditText sign_up_id,sign_up_password,sign_up_email,sign_up_phone_number,sign_up_nickname,sign_up_birthdate,sign_up_gender;
    Button birthdate_button,sign_up_button;

    // 사용자가 입력한 값 관련 선언
    public String id,password,email,phone_number,nickname,birthdate,gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

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
            }
        });

    }

    // 입력한 값을 저장한다.
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

    //날짜 설정
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
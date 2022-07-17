package com.example.fangle.writing.writing_create;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fangle.R;
import com.example.fangle.account.sign_up.SignUpActivity;
import com.example.fangle.account.user_data.UserData;
import com.example.fangle.bulletinboard.bulletinboard_read.BulletinborardListItem;
import com.example.fangle.writing.writing_read.WritingListItem;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WritingCreateActivity extends AppCompatActivity {
    // 파이어베이스 DB연결 관련 선언
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    //DatabaseReference는 데이터베이스의 특정 위치로 연결하는 거라고 생각하면 된다.
    //현재 연결은 데이터베이스에만 딱 연결해놓고
    //키값(테이블 또는 속성)의 위치 까지는 들어가지는 않은 모습이다.
    private DatabaseReference databaseReference = database.getReference();

    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy_MM_dd");

    Button create_button;
    EditText writing_text;
    String receive,result;
    TextView textView;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_create);
        receive = getIntent().getStringExtra("SendData");

        create_button = (Button) findViewById(R.id.create_button);
        writing_text = (EditText) findViewById(R.id.writing_text);
        textView = (TextView) findViewById(R.id.textView);

        userid = UserData.getInstance().getUserID();

        // 버튼을 눌르면 데이터를 DB로 보내야한다. 그리도 엑티비티를 종료한다.
        // 테스트 ) 다이렉트로 데이터를 보낸다.
        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 다이렉트
                result = writing_text.getText().toString();
//                if(result.length() != 0){
//                    Intent intent01 = new Intent();
//                    intent01.putExtra("ResultData", result);
//                    setResult(RESULT_OK, intent01);
//                }else{
//                    setResult(RESULT_CANCELED);
//                }
                addWriting(result,userid,getTime());
                finish();

            }
        });
    }

    // 커뮤니티 -> 게시판 -> 글
    private void addWriting(String result, String userid, String Time){
        //animal.java에서 선언했던 함수.
        WritingListItem WritingListData = new WritingListItem(result,userid,Time);

        //child는 해당 키 위치로 이동하는 함수입니다.
        //키가 없는데 "zoo"와 name같이 값을 지정한 경우 자동으로 생성합니다.
        databaseReference.child("Bulletinboard").child("Name").child("hello").setValue(WritingListData);
    }

    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

}
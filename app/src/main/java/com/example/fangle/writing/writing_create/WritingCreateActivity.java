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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WritingCreateActivity extends AppCompatActivity {

    Button create_button;
    EditText writing_text;
    String receive,result;
    TextView textView;

    // 게시글 데이터를 받을 변수
    String UID,CMT,ID,TTL,DSC,TIM,DAT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_create);
        receive = getIntent().getStringExtra("SendData");

        create_button = (Button) findViewById(R.id.create_button);
        writing_text = (EditText) findViewById(R.id.writing_text);
        textView = (TextView) findViewById(R.id.textView);

        // 버튼을 눌르면 데이터를 DB로 보내야한다. 그리도 엑티비티를 종료한다.
        // 테스트 ) 다이렉트로 데이터를 보낸다.
        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 다이렉트
                result = writing_text.getText().toString();
                if(result.length() != 0){
                    Intent intent01 = new Intent();
                    intent01.putExtra("ResultData", result);
                    setResult(RESULT_OK, intent01);
                }else{
                    setResult(RESULT_CANCELED);
                }
                //
                
                try {
                    String result;
                    WritingCreateActivity.CustomTask task = new WritingCreateActivity.CustomTask();
                    result = task.execute(id,password,email,phone_number,nickname,birthdate,gender,rank).get();
                    Log.i("리턴 값",result);
                }catch (Exception e){

                }

                finish();
            }
        });
    }

    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://222.109.188.220:8080//sign_up/user_data_up.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "UID="+strings[0]
                        +"&PSW="+strings[1]
                        + "&EML="+strings[2]
                        +"&PNB="+strings[3]
                        + "&NCK=" + strings[4]
                        + "&BTD=" +strings[5]
                        +"&GND=" +strings[6]
                        + "&RNK=" + strings[7];
                osw.write(sendMsg);
                osw.flush();
                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "EUC-KR");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();

                } else {
                    Log.i("통신 결과", conn.getResponseCode()+"에러");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return receiveMsg;
        }
    }

    public void writing_data(){
        UID = ""; // 작성자 ID
        CMT = ""; // 게시판 ID
        ID = ""; // 게시글 ID
        TTL = ""; // 게시글 제목
        DSC = writing_text.getText().toString(); // 게시판 내용
        TIM = ""; // 게시판 작성 시간
        DAT = ""; // 게시핀 작성 날짜
    }
}
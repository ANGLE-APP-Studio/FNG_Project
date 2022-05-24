package com.example.fangle.writing.writing_create;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fangle.R;

public class WritingCreateActivity extends AppCompatActivity {

    Button create_button;
    EditText writing_text;
    String receive,result;
    TextView textView;

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


                finish();
            }
        });
    }
}
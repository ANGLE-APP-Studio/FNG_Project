package com.example.fangle.writing.writing_update;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fangle.R;

public class WritingUpdateActivity extends AppCompatActivity {

    EditText writing_text_update;
    Button update_button;

    // 단순한 하게 글을 다시 수정 하면된다.
    // 기존의 글을 intent 하여 에딧 텍스트 위에 올리고 수정 을 하면 DB 에 업데이트 되는 형식
    // 글이 수정이 되면 작성 날짜도 수정이된더.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_update);

        writing_text_update = (EditText) findViewById(R.id.writing_text_update);
        update_button = (Button) findViewById(R.id.update_button);

        // writing_text_update 에 기존의 글을 넣어라

        // 수정 버튼을 눌르면 DB데이터를 수정 하고 아이템을 다시 불러드려라
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
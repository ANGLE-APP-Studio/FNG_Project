package com.example.fangle.writing.writing_update;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fangle.R;

public class WritingUpdateActivity extends AppCompatActivity {


    // 단순한 하게 글을 다시 수정 하면된다.
    // 기존의 글을 intent 하여 에딧 텍스트 위에 올리고 수정 을 하면 DB 에 업데이트 되는 형식
    // 글이 수정이 되면 작성 날짜도 수정이된더.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_update);
    }
}
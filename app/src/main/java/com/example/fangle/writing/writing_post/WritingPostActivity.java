package com.example.fangle.writing.writing_post;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fangle.R;

public class WritingPostActivity extends AppCompatActivity {

    TextView nickname,write_date,writing2;
    EditText comment_create;
    Button comment_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_post);

        nickname = (TextView) findViewById(R.id.nickname);
        write_date = (TextView) findViewById(R.id.write_date);
        writing2 = (TextView) findViewById(R.id.writing2);

        comment_create = (EditText) findViewById(R.id.comment_create);

        comment_button = (Button) findViewById(R.id.create_button);

        Intent writing_intent = getIntent();
        nickname.setText(writing_intent.getStringExtra("nickname"));
        writing2.setText(writing_intent.getStringExtra("writing"));
        write_date.setText(writing_intent.getStringExtra("date_created"));

    }
}
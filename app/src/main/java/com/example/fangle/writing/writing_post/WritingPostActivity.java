package com.example.fangle.writing.writing_post;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fangle.R;
import com.example.fangle.account.user_data.UserData;
import com.example.fangle.comment.comment_read.CommentListItem;
import com.example.fangle.comment.comment_read.CommentListItemAdapter;
import com.example.fangle.writing.writing_read.WritingListItem;

public class WritingPostActivity extends AppCompatActivity {

    TextView nickname,write_date,writing2;
    EditText comment_create;
    Button comment_button;
    ListView bulletinboard_list;
    CommentListItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_post);

        bulletinboard_list = (ListView) findViewById(R.id.bulletinboard_list);
        adapter = new CommentListItemAdapter();

        nickname = (TextView) findViewById(R.id.nickname);
        write_date = (TextView) findViewById(R.id.write_date);
        writing2 = (TextView) findViewById(R.id.writing2);

        String userid = UserData.getInstance().getUserID();
        // 댓글
        comment_create = (EditText) findViewById(R.id.comment_create);
        comment_button = (Button) findViewById(R.id.comment_button);

        Intent writing_intent = getIntent();
        nickname.setText(writing_intent.getStringExtra("nickname"));
        writing2.setText(writing_intent.getStringExtra("writing"));
        write_date.setText(writing_intent.getStringExtra("date_created"));

        comment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = comment_create.getText().toString();
                adapter.addItem(new CommentListItem(userid,comment));
                bulletinboard_list.setAdapter(adapter);

            }
        });

    }
}
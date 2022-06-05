package com.example.fangle.bulletinboard.bulletinboard_update;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fangle.R;

public class BulletinboardUpdateActivity extends Activity {

    Button update_button,cancle_update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletinboard_update);

        update_button = (Button) findViewById(R.id.update_button);
        cancle_update = (Button) findViewById(R.id.cancle_update);

        // 게시판 수정 버튼
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 게시판 수정 취소 버튼
        cancle_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
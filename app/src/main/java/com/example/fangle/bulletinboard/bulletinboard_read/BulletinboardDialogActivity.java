package com.example.fangle.bulletinboard.bulletinboard_read;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fangle.R;
import com.example.fangle.bulletinboard.bulletinboard_update.BulletinboardUpdateActivity;

public class BulletinboardDialogActivity extends Activity {

    Button bulletinboard_update_button,bulletinboard_delete_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletinboard_dialog);

        bulletinboard_delete_button = (Button) findViewById(R.id.bulletinboard_delete_button);
        bulletinboard_update_button = (Button) findViewById(R.id.bulletinboard_update_button);

        // 수정 버튼
        //기존의 게시판 정보를 intent 해서 보내라
        bulletinboard_update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent update = new Intent(BulletinboardDialogActivity.this, BulletinboardUpdateActivity.class);
                startActivity(update);
            }
        });

        // 삭제 버튼
        bulletinboard_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
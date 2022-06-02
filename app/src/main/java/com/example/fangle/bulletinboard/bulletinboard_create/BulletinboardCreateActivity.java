package com.example.fangle.bulletinboard.bulletinboard_create;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.fangle.R;

public class BulletinboardCreateActivity extends Activity {
    String community_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletinboard_create);

        Intent board_read = getIntent();
        community_name = (board_read.getStringExtra("community_name"));

    }
}
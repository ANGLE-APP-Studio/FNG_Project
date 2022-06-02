package com.example.fangle.join;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fangle.R;

public class JoinActivity extends Activity {

    TextView jointext03;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        jointext03 = (TextView)findViewById(R.id.jointext03);

        Intent artist_name = getIntent();

        jointext03.setText(artist_name.getStringExtra("artist_name"));

    }
}
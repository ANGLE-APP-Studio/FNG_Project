package com.example.fangle.main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.fangle.R;

public class CommunityActivity extends AppCompatActivity {

    ImageButton imageButton01, imageButton02, imageButton03, imageButton04, imageButton05, imageButton06,
            imageButton07, imageButton08, imageButton09 ;

    Button btn01, btn02, btn03, btn04, btn05, btn06, btn07, btn08, btn09;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        btn01 = (Button)findViewById(R.id.join01);
        btn02 = (Button)findViewById(R.id.join02);
        btn03 = (Button)findViewById(R.id.join03);
        btn04 = (Button)findViewById(R.id.join04);
        btn05 = (Button)findViewById(R.id.join05);
        btn06 = (Button)findViewById(R.id.join06);
        btn07 = (Button)findViewById(R.id.join07);
        btn08 = (Button)findViewById(R.id.join08);
        btn09 = (Button)findViewById(R.id.join09);




    }

}
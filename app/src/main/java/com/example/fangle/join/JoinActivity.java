package com.example.fangle.join;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.fangle.R;

public class JoinActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn01, btn02;
    RadioButton radiobutton01, radiobutton02;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);


        radiobutton01 = (RadioButton) findViewById(R.id.joinradio01);

        radiobutton02 = (RadioButton) findViewById(R.id.joinradio02);

        btn01 = (Button) findViewById(R.id.joinbutton01);
        btn01.setOnClickListener(this);

        btn02 = (Button) findViewById(R.id.joinbutton02);
        btn02.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


    }
}



package com.example.fangle.bulletinboard.bulletinboard_create;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fangle.R;

public class BulletinboardCreateActivity extends Activity {
    String community_name,result;
    Button cancel_create,create_button;
    EditText board_name_text_create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletinboard_create);
        board_name_text_create = (EditText) findViewById(R.id.board_name_text_create);
        cancel_create = (Button) findViewById(R.id.cancel_create);
        create_button = (Button) findViewById(R.id.create_button);

        cancel_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result = board_name_text_create.getText().toString();
                if(result.length() != 0){
                    Intent intent01 = new Intent();
                    intent01.putExtra("ResultData", result);
                    setResult(RESULT_OK, intent01);
                }else {
                    setResult(RESULT_CANCELED);
                }
                finish();
            }
        });

        Intent board_read = getIntent();
        community_name = (board_read.getStringExtra("community_name"));

    }
}
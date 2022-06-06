package com.example.fangle.bulletinboard.bulletinboard_update;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.example.fangle.R;
import com.example.fangle.bulletinboard.bulletinboard_read.BulletinboardListItemAdapter;
import com.example.fangle.bulletinboard.bulletinboard_read.BulletinborardListItem;

public class BulletinboardUpdateActivity extends Activity {

    String result;
    EditText board_name_text_update;
    Button update_button,cancle_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletinboard_update);

        board_name_text_update = (EditText) findViewById(R.id.board_name_text_update);
        update_button = (Button) findViewById(R.id.update_button);
        cancle_update = (Button) findViewById(R.id.cancle_update);

        Intent get_index = getIntent();
        int index =  get_index.getIntExtra("index",0);
        String Board_name = get_index.getStringExtra("Board_name");

        board_name_text_update.setHint(Board_name);

        // 게시판 수정 버튼
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = board_name_text_update.getText().toString();
                if(result.length() != 0){
                    Intent intent01 = new Intent();
                    intent01.putExtra("ResultData", result);
                    intent01.putExtra("index",index);
                    setResult(RESULT_OK, intent01);
                }else {
                    setResult(RESULT_CANCELED);
                }
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
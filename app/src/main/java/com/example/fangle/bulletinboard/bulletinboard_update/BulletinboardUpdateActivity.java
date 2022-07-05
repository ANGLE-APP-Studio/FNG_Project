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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BulletinboardUpdateActivity extends Activity {

    String result,Board_name;
    EditText board_name_text_update;
    Button update_button,cancle_update;

    // 파이어베이스 DB연결 관련 선언
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    //DatabaseReference는 데이터베이스의 특정 위치로 연결하는 거라고 생각하면 된다.
    //현재 연결은 데이터베이스에만 딱 연결해놓고
    //키값(테이블 또는 속성)의 위치 까지는 들어가지는 않은 모습이다.
    private DatabaseReference databaseReference = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletinboard_update);

        board_name_text_update = (EditText) findViewById(R.id.board_name_text_update);
        update_button = (Button) findViewById(R.id.update_button);
        cancle_update = (Button) findViewById(R.id.cancle_update);

        Intent get_index = getIntent();
        int index =  get_index.getIntExtra("index",0);
        Board_name = get_index.getStringExtra("Board_name");

        board_name_text_update.setHint(Board_name);

        // 게시판 수정 버튼
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = board_name_text_update.getText().toString();
                /*if(result.length() != 0){
                    Intent intent01 = new Intent();
                    intent01.putExtra("ResultData", result);
                    intent01.putExtra("index",index);
                    setResult(RESULT_OK, intent01);
                }else {
                    setResult(RESULT_CANCELED);
                }*/
                updateBulletinboard(result);
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

    private void updateBulletinboard(String result){
        //animal.java에서 선언했던 함수.
        BulletinborardListItem BulletinboardData = new BulletinborardListItem(result);

        //child는 해당 키 위치로 이동하는 함수입니다.
        //키가 없는데 "zoo"와 name같이 값을 지정한 경우 자동으로 생성합니다.
        databaseReference.child("Bulletinboard").child(Board_name).setValue(BulletinboardData);
    }

}
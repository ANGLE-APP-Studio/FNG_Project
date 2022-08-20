package com.example.fangle.bulletinboard.bulletinboard_create;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.fangle.R;
import com.example.fangle.account.user_data.UserData;
import com.example.fangle.bulletinboard.bulletinboard_read.BulletinboardListItemAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.fangle.bulletinboard.bulletinboard_read.BulletinborardListItem;

public class BulletinboardCreateActivity extends Activity {

    // 파이어베이스 DB연결 관련 선언
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    //DatabaseReference는 데이터베이스의 특정 위치로 연결하는 거라고 생각하면 된다.
    //현재 연결은 데이터베이스에만 딱 연결해놓고
    //키값(테이블 또는 속성)의 위치 까지는 들어가지는 않은 모습이다.
    private DatabaseReference databaseReference = database.getReference();

    BulletinboardListItemAdapter adapter;
    String community_name,result;
    String rng = "";
    Button cancel_create,create_button;
    EditText board_name_text_create;
    RadioGroup rdgGroup;
    RadioButton radioButton1,radioButton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletinboard_create);
        board_name_text_create = (EditText) findViewById(R.id.board_name_text_create);
        cancel_create = (Button) findViewById(R.id.cancel_create);
        create_button = (Button) findViewById(R.id.create_button);



        //adapter 참조
        adapter = new BulletinboardListItemAdapter();

        cancel_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_rng();
               result = board_name_text_create.getText().toString();
               addBulletinboard(result,rng);
               finish();
            }
        });

        Intent board_read = getIntent();
        community_name = (board_read.getStringExtra("community_name"));

    }

    private void addBulletinboard(String result,String rng){
        //animal.java에서 선언했던 함수.
        BulletinborardListItem BulletinboardData = new BulletinborardListItem(result,rng);

        //child는 해당 키 위치로 이동하는 함수입니다.
        //키가 없는데 "zoo"와 name같이 값을 지정한 경우 자동으로 생성합니다.
        databaseReference.child(community_name).child("Bulletinboard").child(result).setValue(BulletinboardData);
    }

    private String get_rng(){
        radioButton1 = (RadioButton) findViewById(R.id.member_free_create);
        radioButton2 = (RadioButton) findViewById(R.id.member_pay_create);

        if(radioButton1.isChecked()){
            rng = radioButton1.getText().toString();
        }else if (radioButton2.isChecked()){
            rng = radioButton2.getText().toString();
        }
        return rng;
    }

}
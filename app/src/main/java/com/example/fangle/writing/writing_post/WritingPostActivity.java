package com.example.fangle.writing.writing_post;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fangle.R;
import com.example.fangle.account.user_data.UserData;
import com.example.fangle.comment.comment_read.CommentListItem;
import com.example.fangle.comment.comment_read.CommentListItemAdapter;
import com.example.fangle.community.community_data.CommunityData;
import com.example.fangle.writing.writing_read.WritingListItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WritingPostActivity extends AppCompatActivity {

    // 파이어베이스 DB연결 관련 선언
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    //DatabaseReference는 데이터베이스의 특정 위치로 연결하는 거라고 생각하면 된다.
    //현재 연결은 데이터베이스에만 딱 연결해놓고
    //키값(테이블 또는 속성)의 위치 까지는 들어가지는 않은 모습이다.
    private DatabaseReference databaseReference = database.getReference();

    TextView nickname,write_date,writing2,write_title;
    EditText comment_create;
    Button comment_button;
    ListView bulletinboard_list;
    CommentListItemAdapter adapter;
    String community_name="";
    String board_name="";
    String write="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_post);

        bulletinboard_list = (ListView) findViewById(R.id.bulletinboard_list);
        adapter = new CommentListItemAdapter();

        write_title = (TextView) findViewById(R.id.writing_title_post);
        nickname = (TextView) findViewById(R.id.nickname);
        write_date = (TextView) findViewById(R.id.write_date);
        writing2 = (TextView) findViewById(R.id.writing2);

        String userid = UserData.getInstance().getUserID();
        community_name = CommunityData.getInstance().getCommunity_name();

        // 댓글
        comment_create = (EditText) findViewById(R.id.comment_create);
        comment_button = (Button) findViewById(R.id.comment_button);

        Intent writing_intent = getIntent();
        board_name = writing_intent.getStringExtra("board_name");
        write_title.setText(writing_intent.getStringExtra("writing_title"));
        write = writing_intent.getStringExtra("writing_title");
        nickname.setText(writing_intent.getStringExtra("nickname"));
        writing2.setText(writing_intent.getStringExtra("writing"));
        write_date.setText(writing_intent.getStringExtra("date_created"));

        bulletinboard_list.setAdapter(adapter);

        readcomment();

        comment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = comment_create.getText().toString();
                addcomment(comment,userid);
                list_clear();
                //readcomment();

            }
        });

    }

    public void addcomment(String comment, String userid){
        //animal.java에서 선언했던 함수.
        CommentListItem CommentListData = new CommentListItem(userid,comment);

        //child는 해당 키 위치로 이동하는 함수입니다.
        //키가 없는데 "zoo"와 name같이 값을 지정한 경우 자동으로 생성합니다.
        databaseReference.child(community_name).child("Bulletinboard").child(board_name).child("Wring").child(write).child("Comment").child(comment).setValue(CommentListData);
    }

    public void readcomment(){
        databaseReference.child(community_name).child("Bulletinboard").child(board_name).child("Wring").child(write).child("Comment").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //WritingListItem group = snapshot.getValue(WritingListItem.class);
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String userid = snapshot.child("userID").getValue(String.class);
                    String comment = snapshot.child("comment").getValue(String.class);
                    adapter.addItem(new CommentListItem(userid,comment));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });
    }

    public void list_clear(){
        int count = adapter.getCount();
        for(int i = 0;i<count;i++){
            adapter.clear();
        }
    }

}
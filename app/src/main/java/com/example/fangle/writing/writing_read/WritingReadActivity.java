package com.example.fangle.writing.writing_read;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fangle.R;
import com.example.fangle.account.user_data.UserData;
import com.example.fangle.bulletinboard.bulletinboard_read.BulletinboardListItemAdapter;
import com.example.fangle.bulletinboard.bulletinboard_read.BulletinborardListItem;
import com.example.fangle.bulletinboard.bulletinboard_update.BulletinboardUpdateActivity;
import com.example.fangle.writing.writing_create.WritingCreateActivity;
import com.example.fangle.writing.writing_post.WritingPostActivity;
import com.example.fangle.writing.writing_update.WritingUpdateActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class WritingReadActivity extends AppCompatActivity {
    // 파이어베이스 DB연결 관련 선언
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    //DatabaseReference는 데이터베이스의 특정 위치로 연결하는 거라고 생각하면 된다.
    //현재 연결은 데이터베이스에만 딱 연결해놓고
    //키값(테이블 또는 속성)의 위치 까지는 들어가지는 않은 모습이다.
    private DatabaseReference databaseReference = database.getReference();
    private DatabaseReference child = databaseReference.child("Bulletinboard").child("Name").child("Wring");

    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");

    private ActivityResultLauncher<Intent> resultLauncher,updateLauncher;

    public ListView writing_list;
    WritingListItemAdapter adapter;
    BulletinboardListItemAdapter Bulletinboard_adapter;
    UserData userData;
    TextView board_name_text;

    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_read);

        board_name_text = (TextView) findViewById(R.id.board_name_text);

        // listview 참조
        writing_list = (ListView) findViewById(R.id.bulletinboard_list);
        registerForContextMenu(writing_list);
        //adapter 참조
        adapter = new WritingListItemAdapter();

        userid = UserData.getInstance().getUserID();

        Intent bulletinboardRead_intent = getIntent();
        board_name_text.setText(bulletinboardRead_intent.getStringExtra("board_name"));

        writing_list.setAdapter(adapter);

        writing_list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){

                //  리스트 아이템 에서
                String Writing_title = ((WritingListItem)adapter.getItem(position)).getWriting_title();
                String Writing = ((WritingListItem)adapter.getItem(position)).getWriting();
                String nickname = ((WritingListItem)adapter.getItem(position)).getNickname();
                String date_created = ((WritingListItem)adapter.getItem(position)).getDate_Created();

                // 클릭시 클 크게 보기
                Intent post_intent = new Intent(WritingReadActivity.this, WritingPostActivity.class);
                post_intent.putExtra("writing_title",Writing_title);
                post_intent.putExtra("nickname",nickname);
                post_intent.putExtra("writing",Writing);
                post_intent.putExtra("date_created",date_created);
                startActivity(post_intent);

            }
        });

//        writing_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
//            @Override
//            public boolean onItemLongClick(AdapterView parent, View v, int position, long id){
//                // 롱 클릭시 수정 삭제
//                // 다이얼 로그를 불러들이고 그 안에 수정 삭제 기능을 넣는다.
//                Intent writing_dialog = new Intent(WritingReadActivity.this,WritingDialogActivity.class);
//                startActivity(writing_dialog);
//                return true;
//            }
//        });

        child.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //WritingListItem group = snapshot.getValue(WritingListItem.class);
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String writing = snapshot.child("writing").getValue(String.class);
                    String nickname = snapshot.child("nickname").getValue(String.class);
                    String date_created = snapshot.child("date_Created").getValue(String.class);
                    String writing_title = snapshot.child("writing_title").getValue(String.class);
                    adapter.addItem(new WritingListItem(writing,nickname,date_created,writing_title));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.option_menu, menu);
    }

    //여기가 수정 삭제 부분
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int index= info.position;
        String writing_text = ((WritingListItem)adapter.getItem(index)).getWriting();
        String nickname = ((WritingListItem)adapter.getItem(index)).getNickname();
        if(nickname == userid){
            int itemId = item.getItemId();
            if(itemId == R.id.update){
                Intent update = new Intent(this, WritingUpdateActivity.class);
                update.putExtra("writing_text",writing_text);
                update.putExtra("index",index);
                updateLauncher.launch(update);
            }else if(itemId == R.id.delete){
                adapter.remove(index);
                adapter.notifyDataSetChanged();
            }
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("접근권한");
            builder.setMessage("수정 권한이 없습니다.");
            builder.setPositiveButton("예",null);
            builder.create().show();
        }

        return super.onContextItemSelected(item);
    }

    // 너무 많이 쓴다 전역 변수로 만들어라
    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

    // 글쓰는 엑티비티로 넘어가기
    public void writing_create_button(View view){
        list_clear();
        Intent writing_create_intent = new Intent(WritingReadActivity.this, WritingCreateActivity.class);
        writing_create_intent.putExtra("SendData",userid);
        startActivity(writing_create_intent);
    }

    public void list_clear(){
        int count = adapter.getCount();
        for(int i = 0;i<count;i++){
            adapter.clear();
        }
    }

}
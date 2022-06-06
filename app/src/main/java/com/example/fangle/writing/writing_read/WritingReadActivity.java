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
import com.example.fangle.bulletinboard.bulletinboard_read.BulletinborardListItem;
import com.example.fangle.bulletinboard.bulletinboard_update.BulletinboardUpdateActivity;
import com.example.fangle.writing.writing_create.WritingCreateActivity;
import com.example.fangle.writing.writing_post.WritingPostActivity;
import com.example.fangle.writing.writing_update.WritingUpdateActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WritingReadActivity extends AppCompatActivity {

    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");

    private ActivityResultLauncher<Intent> resultLauncher,updateLauncher;

    public ListView writing_list;
    WritingListItemAdapter adapter;
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

        adapter.addItem(new WritingListItem("동요","나개똥",getTime()));
        writing_list.setAdapter(adapter);

        writing_list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){

                //  리스트 아이템 에서
                String Writing = ((WritingListItem)adapter.getItem(position)).getWriting();
                String nickname = ((WritingListItem)adapter.getItem(position)).getNickname();
                String date_created = ((WritingListItem)adapter.getItem(position)).getDate_Created();

                // 클릭시 클 크게 보기
                Intent post_intent = new Intent(WritingReadActivity.this, WritingPostActivity.class);
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

        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK){
                    Intent data_intent = result.getData();
                    String data_result = data_intent.getExtras().getString("ResultData");
                    adapter.addItem(new WritingListItem(data_result,userid,getTime()));
                    writing_list.setAdapter(adapter);
                }
            }
        });

        updateLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK){
                    Intent data_intent = result.getData();
                    String data_result = data_intent.getExtras().getString("ResultData");
                    int index = data_intent.getExtras().getInt("index");
                    WritingListItem name = new WritingListItem(data_result,userid,getTime());
                    adapter.set(index,name);
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.option_menu, menu);
    }

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

    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

    // 글쓰는 엑티비티로 넘어가기
    public void writing_create_button(View view){
        Intent writing_create_intent = new Intent(WritingReadActivity.this, WritingCreateActivity.class);
        writing_create_intent.putExtra("SendData","이규현");
        resultLauncher.launch(writing_create_intent);
    }

}
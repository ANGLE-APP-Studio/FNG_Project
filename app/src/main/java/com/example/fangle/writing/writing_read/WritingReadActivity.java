package com.example.fangle.writing.writing_read;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fangle.R;
import com.example.fangle.writing.writing_create.WritingCreateActivity;
import com.example.fangle.writing.writing_post.WritingPostActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WritingReadActivity extends AppCompatActivity {

    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");

    private ActivityResultLauncher<Intent> resultLauncher;

    ListView writing_list;
    WritingListItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_read);

        // listview 참조
        writing_list = (ListView) findViewById(R.id.comment_list);
        //adapter 참조
        adapter = new WritingListItemAdapter();


        adapter.addItem(new WritingListItem("오늘입니다","분홍신",getTime()));
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

        writing_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView parent, View v, int position, long id){
                // 롱 클릭시 수정 삭제
                // 다이얼 로그를 불러들이고 그 안에 수정 삭제 기능을 넣는다.
                Intent writing_dialog = new Intent(WritingReadActivity.this,WritingDialogActivity.class);
                startActivity(writing_dialog);
                return true;
            }
        });

//        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//            @Override
//            public void onActivityResult(ActivityResult result) {
//                if(result.getResultCode() == RESULT_OK){
//                    Intent data_intent = result.getData();
//                    String data_result = data_intent.getExtras().getString("ResultData");
//                    adapter.addItem(new WritingListItem(data_result,"봄"));
//                    writing_list.setAdapter(adapter);
//                }
//                if(result.getResultCode() != RESULT_OK){
//                    adapter.addItem(new WritingListItem("NoData","봄"));
//                    writing_list.setAdapter(adapter);
//                }
//            }
//        });


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
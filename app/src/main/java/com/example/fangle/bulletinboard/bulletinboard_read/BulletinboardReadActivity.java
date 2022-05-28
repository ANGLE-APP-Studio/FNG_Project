package com.example.fangle.bulletinboard.bulletinboard_read;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.fangle.R;
import com.example.fangle.writing.writing_post.WritingPostActivity;
import com.example.fangle.writing.writing_read.WritingListItem;
import com.example.fangle.writing.writing_read.WritingReadActivity;

public class BulletinboardReadActivity extends AppCompatActivity {

    ListView board_list;
    BulletinboardListItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletinboard_read);

        // listview 참조
        board_list = (ListView) findViewById(R.id.bulletinboard_list);
        //adapter 참조
        adapter = new BulletinboardListItemAdapter();

        adapter.addItem(new BulletinborardListItem("Hello"));
        board_list.setAdapter(adapter);

        board_list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){

                //  리스트 아이템 에서
                String board_name = ((BulletinborardListItem)adapter.getItem(position)).getBoard_name();

                // 클릭시 게시글로 넘어감
                Intent post_intent = new Intent(BulletinboardReadActivity.this, WritingReadActivity.class);
                post_intent.putExtra("board_name",board_name);
                startActivity(post_intent);

            }
        });

    }
}
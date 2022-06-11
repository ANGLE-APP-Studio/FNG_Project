package com.example.fangle.announcement.announcement_read;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fangle.R;
import com.example.fangle.announcement.announcement_post.AnnounCementPostActivity;
import com.example.fangle.writing.writing_post.WritingPostActivity;
import com.example.fangle.writing.writing_read.WritingListItem;
import com.example.fangle.writing.writing_read.WritingReadActivity;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AnnounCementReadActivity extends AppCompatActivity {

    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ListView announcement_list;
    TextView announcement_name_text;
    AnnounCementListItemAdapter adapter;
    String image_text;
    int image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_read);
        announcement_name_text = (TextView) findViewById(R.id.announcement_name_text);

        adapter = new AnnounCementListItemAdapter();
        announcement_list = (ListView) findViewById(R.id.announcement_list);

        Intent announcement_intent = getIntent();
        announcement_name_text.setText(announcement_intent.getStringExtra("board_name"));
        image_text = announcement_intent.getStringExtra("image_text");

        switch (image_text){
            case "NMIXX":
                adapter.addItem(new AnnounCementListItem("첫 공지!!",getTime(),R.drawable.nmixx_announcrment));
                adapter.notifyDataSetChanged();
                break;
            case  "Stray_Kids":
                adapter.addItem(new AnnounCementListItem("첫 공지!!",getTime(),R.drawable.skz_announcrment));
                adapter.notifyDataSetChanged();
                break;
            case "ITZY":
                adapter.addItem(new AnnounCementListItem("첫 공지!!",getTime(),R.drawable.itzy_announcrment));
                adapter.notifyDataSetChanged();
                break;
        }

        announcement_list.setAdapter(adapter);

        announcement_list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){

                //  리스트 아이템 에서
                String Announcement_title = ((AnnounCementListItem)adapter.getItem(position)).getAnnouncement_title();
                String date_created = ((AnnounCementListItem)adapter.getItem(position)).getDate_Created();
                int  Announcement_image = ((AnnounCementListItem)adapter.getItem(position)).getAnnouncement_image();

                // 클릭시 클 크게 보기
                Intent post_intent = new Intent(AnnounCementReadActivity.this, AnnounCementPostActivity.class);
                post_intent.putExtra("Announcement_title",Announcement_title);
                post_intent.putExtra("date_created",date_created);
                post_intent.putExtra("Announcement_image",Announcement_image);
                startActivity(post_intent);

            }
        });
    }

    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

}

package com.example.fangle.announcement.announcement_post;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fangle.R;

public class AnnounCementPostActivity extends AppCompatActivity {
    TextView announcement_title_post,Date_Created_post;
    ImageView announcement_image_post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announ_cement_post);
        announcement_title_post = (TextView) findViewById(R.id.announcement_title_post);
        Date_Created_post = (TextView) findViewById(R.id.Date_Created_post);
        announcement_image_post = (ImageView) findViewById(R.id.announcement_image_post);

        Intent announcement_post = getIntent();
        announcement_title_post.setText(announcement_post.getStringExtra("Announcement_title"));
        Date_Created_post.setText((announcement_post.getStringExtra("date_created")));
        announcement_image_post.setImageResource(announcement_post.getIntExtra("Announcement_image",0));

    }
}
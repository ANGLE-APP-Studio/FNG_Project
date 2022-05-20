package com.example.fangle.main.main_read;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import androidx.appcompat.widget.Toolbar;

import com.example.fangle.R;

public class MainReadActivity extends AppCompatActivity {

    Toolbar toolbar;
    public CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_read);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        // 홈버튼 활성화 및 홈버튼 이미지 변경
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);

        getSupportActionBar().setDisplayShowCustomEnabled(false);

        // 배너 뷰 타이머
        final HorizontalScrollView HorizontalScrollView = ((HorizontalScrollView) findViewById(R.id.HorizontalScrollView));
        HorizontalScrollView.post(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator.ofInt(HorizontalScrollView, "scrollX", 10000).setDuration(100000).start();

            }
        });

    }

    // 상단 툴바 추가
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    // 상단 툴바 클릭 처리
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){

        }
        if (id == R.id.setting_item){

        }
        return super.onOptionsItemSelected(item);
    }

}

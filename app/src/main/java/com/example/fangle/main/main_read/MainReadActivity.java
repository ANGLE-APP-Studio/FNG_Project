package com.example.fangle.main.main_read;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

import com.example.fangle.R;

public class MainReadActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar ab = getSupportActionBar() ;
        setContentView(R.layout.activity_main_read);

        final HorizontalScrollView HorizontalScrollView = ((HorizontalScrollView) findViewById(R.id.HorizontalScrollView));
        HorizontalScrollView.post(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator.ofInt(HorizontalScrollView, "scrollX", 10000).setDuration(100000).start();

            }

        });
    }

}

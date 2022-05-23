package com.example.fangle.main.main_read;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.fangle.profile.profile_read.ProfileReadFragment;
import com.example.fangle.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainReadActivity extends AppCompatActivity {

    Toolbar toolbar;

    public CalendarView calendarView;

    // 하단바 Fragment
    ProfileReadFragment profileReadFragment;
    MainReadFragment mainReadFragment;

    // 여기에 입력

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_read);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //프레그면트
        profileReadFragment = new ProfileReadFragment();
        mainReadFragment = new MainReadFragment();


        getSupportFragmentManager().beginTransaction().replace(R.id.container, mainReadFragment).commit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home_tab);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home_tab:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, mainReadFragment).commit();
                        return true;
                    case R.id.account_tab:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, profileReadFragment).commit();
                        return true;
                }
                return false;
            }
        });




        // 홈버튼 활성화 및 홈버튼 이미지 변경
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);
        getSupportActionBar().setDisplayShowCustomEnabled(false);

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
            Toast.makeText(this,"메뉴", Toast.LENGTH_LONG).show();
        }
        if (id == R.id.setting_item){
            Toast.makeText(this,"셋팅", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

}

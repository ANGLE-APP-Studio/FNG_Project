package com.example.fangle.main.main_read;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.fangle.community.community_read.CommunityReadFragment;
import com.example.fangle.nft.nft_read.NftReadActivity;
import com.example.fangle.profile.profile_read.ProfileReadFragment;
import com.example.fangle.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainReadActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    public CalendarView calendarView;

    // 하단바 Fragment
    ProfileReadFragment profileReadFragment;
    MainReadFragment mainReadFragment;
    CommunityReadFragment communityReadFragment;

    // 여기에 입력

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //프레그면트
        profileReadFragment = new ProfileReadFragment();
        mainReadFragment = new MainReadFragment();
        communityReadFragment = new CommunityReadFragment();

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
                    case R.id.community_tab:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, communityReadFragment).commit();
                        return true;
                }
                return false;
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
               switch (menuItem.getItemId()){
                   case R.id.account://커뮤니티
                       Toast.makeText(MainReadActivity.this,"커뮤니티", Toast.LENGTH_LONG).show();
                   case R.id.star: // 일정표

                   case R.id.cart: // NFT
                       Intent nft_intent = new Intent(MainReadActivity.this, NftReadActivity.class);
                       startActivity(nft_intent);
                   case R.id.bug_report: // 알람

                   case R.id.profile: // 프로필

                   case R.id.logout: // 로그아웃

               }

                return true;
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
            drawerLayout.openDrawer(GravityCompat.START);
        }
        if (id == R.id.setting_item){
            Toast.makeText(this,"셋팅", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

}

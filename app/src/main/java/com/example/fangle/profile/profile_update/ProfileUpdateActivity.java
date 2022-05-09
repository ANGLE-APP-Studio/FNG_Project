package com.example.fangle.profile.profile_update;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.fangle.R;

public class ProfileUpdateActivity extends Activity {
    EditText user_phone_number,user_nickname,user_birthdate,user_gender;
    Button user_profile_update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);

        user_phone_number = (EditText) findViewById(R.id.user_phone_number);
        user_nickname = (EditText) findViewById(R.id.user_nickname);
        user_birthdate = (EditText) findViewById(R.id.user_birthdate);
        user_gender = (EditText) findViewById(R.id.user_gender);

        user_profile_update = (Button) findViewById(R.id.user_profile_update);

        user_profile_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

    }
}
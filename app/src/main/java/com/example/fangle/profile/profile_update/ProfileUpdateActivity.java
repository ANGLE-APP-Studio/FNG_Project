package com.example.fangle.profile.profile_update;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.fangle.R;
import com.example.fangle.account.user_data.UserData;

public class ProfileUpdateActivity extends Activity {
    EditText user_phone_number,user_nickname,user_birthdate,user_gender;
    Button user_profile_update;
    String result;
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

                result = user_nickname.getText().toString();
                if(result.length() != 0){
                    Intent intent01 = new Intent();
                    intent01.putExtra("ResultData", result);
                    setResult(RESULT_OK, intent01);
                }else{
                    setResult(RESULT_CANCELED);
                }

                UserData.getInstance().setUserID(result);
                finish();
            }
        });

    }
}
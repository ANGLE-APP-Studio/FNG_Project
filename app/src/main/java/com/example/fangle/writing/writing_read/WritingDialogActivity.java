package com.example.fangle.writing.writing_read;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fangle.R;
import com.example.fangle.writing.writing_update.WritingUpdateActivity;

public class WritingDialogActivity extends Activity {

    Button writing_update_button,writing_delete_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_dialog);

        writing_update_button = (Button) findViewById(R.id.writing_update_button);
        writing_delete_button = (Button) findViewById(R.id.writing_delete_button);

        // 수정 버튼을 눌르면
        // 기존의 글을 가진상태에서 수정을 해야한다.
        writing_update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent update = new Intent(WritingDialogActivity.this, WritingUpdateActivity.class);
                startActivity(update);
                finish();
            }
        });

        // 해당 아이템을 삭제 시킨다.
        // 아이템 위치를 가져와야 될것 이다.
        writing_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
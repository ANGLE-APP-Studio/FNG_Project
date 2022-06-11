package com.example.fangle.calendar.calendar_read;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fangle.R;

public class CalendarReadActivity extends Activity {
    TextView data_time,calendar_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_read);
        data_time = (TextView) findViewById(R.id.date_time);
        calendar_text = (TextView) findViewById(R.id.calendar_text);

        Intent calendar_intent = getIntent();
        data_time.setText(calendar_intent.getStringExtra("dayOfMonth"));
        calendar_text.setText(calendar_intent.getStringExtra("calendar_text"));

    }
}
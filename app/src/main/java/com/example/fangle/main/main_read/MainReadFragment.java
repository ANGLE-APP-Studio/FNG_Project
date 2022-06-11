package com.example.fangle.main.main_read;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.HorizontalScrollView;

import com.example.fangle.R;
import com.example.fangle.calendar.calendar_read.CalendarReadActivity;

public class MainReadFragment extends Fragment {

    CalendarView calendarView;
    String calendar_text = "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_read, container, false);
        calendarView = (CalendarView) v.findViewById(R.id.calendarView);

        // 배너 뷰 타이머
        final HorizontalScrollView HorizontalScrollView = ((HorizontalScrollView) v.findViewById(R.id.HorizontalScrollView));
        HorizontalScrollView.post(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator.ofInt(HorizontalScrollView, "scrollX", 10000).setDuration(100000).start();
            }
        });

        // 캘린더 선택시
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                    int rmonth = month+1;
                    String day = year + "년" + "_" + rmonth +"월" + "_" + dayOfMonth +"일";

                    if(dayOfMonth == 20 && rmonth == 6){
                        calendar_text = "itzy" + "\n"
                                +"(ETC) <CHECKMATE> CONCEPT FILM #2";
                    }else if(dayOfMonth == 13 && rmonth == 6){
                        calendar_text = "itzy" + "\n"
                                +"(ETC) <CHECKMATE> CONCEPT PHOTO #1";
                    }else if(dayOfMonth == 19 && rmonth == 6){
                        calendar_text = "itzy" + "\n"
                                +"(ETC) [●LIVE] 류진";
                    }else{
                        calendar_text = "";
                    }

                    Intent calendar_intent = new Intent(getActivity(), CalendarReadActivity.class);
                    calendar_intent.putExtra("dayOfMonth",day);
                    calendar_intent.putExtra("calendar_text",calendar_text);
                    startActivity(calendar_intent);

            }
        });

        return v;
    }

}
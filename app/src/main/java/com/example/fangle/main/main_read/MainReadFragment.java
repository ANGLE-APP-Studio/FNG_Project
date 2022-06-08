package com.example.fangle.main.main_read;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.example.fangle.R;

public class MainReadFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_read, container, false);

        // 배너 뷰 타이머
        final HorizontalScrollView HorizontalScrollView = ((HorizontalScrollView) v.findViewById(R.id.HorizontalScrollView));
        HorizontalScrollView.post(new Runnable() {
            @Override
            public void run() {

                ObjectAnimator.ofInt(HorizontalScrollView, "scrollX", 10000).setDuration(100000).start();

            }
        });

        return v;
    }

}
package com.example.kalle.workoutdiary;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

public class DiaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void setUp() {
        LinearLayout layout = new LinearLayout(this);
        setContentView(layout);

        layout.setBackgroundColor(Color.rgb(43,43,43));


    }
}

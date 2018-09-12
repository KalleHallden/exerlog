package com.example.kalle.workoutdiary;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class BottomNavigation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("We got to bottom nav");
        setUp();
    }

    public void setUp() {
        LinearLayout layout = new LinearLayout(this);
        setContentView(layout);
        layout.setBackgroundColor(Color.rgb(43,43,43));
    }
}

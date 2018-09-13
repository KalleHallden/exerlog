package com.example.kalle.workoutdiary;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;

public class BottomNavigationBar {
    Context context;
    LinearLayout bottomNavBarItems = new LinearLayout(context);

    public void makeBottomnavBar(LinearLayout layout, int width) {
        layout.setMinimumHeight(150);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setMinimumWidth(width);
        LinearLayout stats = new LinearLayout(context);
        stats.setBackgroundColor(Color.BLUE);
        stats.setMinimumWidth(width / 3);
        LinearLayout addWorkout = new LinearLayout(context);
        addWorkout.setBackgroundColor(Color.WHITE);
        addWorkout.setMinimumWidth(width / 3);
        LinearLayout diary = new LinearLayout(context);
        diary.setBackgroundColor(Color.GREEN);
        addWorkout.setMinimumWidth(width / 3);
        layout.addView(stats);
        layout.addView(addWorkout);
        layout.addView(diary);
    }

    public void setContext(Context t) {
        context = t;
    }
}

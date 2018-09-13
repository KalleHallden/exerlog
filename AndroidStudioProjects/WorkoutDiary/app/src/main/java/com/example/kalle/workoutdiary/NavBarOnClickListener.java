package com.example.kalle.workoutdiary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

public class NavBarOnClickListener implements View.OnClickListener {

    int id;

    public NavBarOnClickListener(int name) {
        id = name;
    }

    public NavBarOnClickListener() {
    }

    @Override
    public void onClick(View v) {
        System.out.println("Row " + id + " clicked");
        Context context = v.getContext();
        if (id == 1) {
            Intent i = new Intent(context, StatsActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else if (id == 2) {
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else if (id == 3) {
            Intent i = new Intent(context, DiaryActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}


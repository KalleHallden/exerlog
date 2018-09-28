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
            BottomNaviClass.id = id;
            Intent i = new Intent(context, BottomNaviClass.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else if (id == 2) {
            BottomNaviClass.id = id;
            Intent i = new Intent(context, BottomNaviClass.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
            SaveWorkout test = new SaveWorkout();
            test.openWorkout(0, v.getContext());
        } else if (id == 3) {
            BottomNaviClass.id = id;
            SaveWorkout.checkExistingFiles(v.getContext());
            Intent i = new Intent(context, BottomNaviClass.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else if (id == 4) {
            BottomNaviClass.id = id;
            SaveBodyWeight.checkExistingFiles(v.getContext());
            Intent i = new Intent(context, BottomNaviClass.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else if (id == 5) {
            BottomNaviClass.id = id;
            Intent i = new Intent(context, BottomNaviClass.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }

    }
}


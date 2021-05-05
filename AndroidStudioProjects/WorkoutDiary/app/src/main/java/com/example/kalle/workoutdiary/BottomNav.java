package com.example.kalle.workoutdiary;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class BottomNav {

    static ArrayList<ImageButton> buttonList;

    static ImageButton addWorkout;
    static ImageButton diary;
    static ImageButton stats;
    static ImageButton bodyStats;
    static ImageButton profile;
    public static int caller;

    public static void makeBottomnavBar(LinearLayout layout, int widths, Context context, int call) {
        buttonList = new ArrayList<ImageButton>();

        addWorkout = new ImageButton(context);
        diary = new ImageButton(context);
        stats = new ImageButton(context);
        bodyStats = new ImageButton(context);
        profile = new ImageButton(context);

        buttonList.add(stats);
        buttonList.add(profile);
        buttonList.add(addWorkout);
        buttonList.add(diary);
        buttonList.add(bodyStats);


        diary.setOnClickListener(new NavBarOnClickListener(3));


        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setMinimumWidth(widths);


        LinearLayout.LayoutParams layoutpara = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutpara.gravity = Gravity.CENTER;
        LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        para.gravity = Gravity.CENTER;


        stats.setMinimumWidth(widths / 5);
        System.out.println("This is width: " + (widths / 4));
        stats.setMinimumHeight(160);
        stats.setImageResource(R.drawable.stats_image);
        stats.setColorFilter(BottomNaviClass.green);
        stats.setBackgroundColor(BottomNaviClass.lightBlack);

        bodyStats.setMinimumWidth(widths / 5);
        bodyStats.setMinimumHeight(160);
        bodyStats.setLayoutParams(layoutpara);
        bodyStats.setImageResource(R.drawable.weight_image);
        bodyStats.setColorFilter(BottomNaviClass.green);
        bodyStats.setBackgroundColor(BottomNaviClass.lightBlack);

        diary.setMinimumWidth(widths / 5);
        diary.setMinimumHeight(160);
        diary.setImageResource(R.drawable.diary_image);
        diary.setColorFilter(BottomNaviClass.green);
        diary.setBackgroundColor(BottomNaviClass.lightBlack);


        addWorkout.setMinimumWidth(widths / 5);
        addWorkout.setMinimumHeight(160);
        addWorkout.setLayoutParams(layoutpara);
        addWorkout.setImageResource(R.drawable.add_image);
        addWorkout.setColorFilter(BottomNaviClass.green);
        addWorkout.setBackgroundColor(BottomNaviClass.lightBlack);

        profile.setMinimumWidth(widths / 5);
        profile.setMinimumHeight(160);
        profile.setLayoutParams(layoutpara);
        profile.setImageResource(R.drawable.tick_image);
        profile.setColorFilter(BottomNaviClass.green);
        profile.setBackgroundColor(BottomNaviClass.lightBlack);


        layout.addView(stats);
        layout.addView(profile);
        layout.addView(addWorkout);
        layout.addView(diary);
        layout.addView(bodyStats);
    }

}

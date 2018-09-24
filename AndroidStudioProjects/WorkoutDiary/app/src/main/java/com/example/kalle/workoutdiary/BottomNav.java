package com.example.kalle.workoutdiary;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BottomNav {
    static ImageButton addWorkout;
    static ImageButton diary;
    static ImageButton stats;
    static ImageButton bodyStats;
    static ImageButton profile;

    public static void makeBottomnavBar(LinearLayout layout, int widths, Context context, int caller) {


        addWorkout = new ImageButton(context);
        diary = new ImageButton(context);
        stats = new ImageButton(context);
        bodyStats = new ImageButton(context);
        profile = new ImageButton(context);



        stats.setOnClickListener(new NavBarOnClickListener(1));
        addWorkout.setOnClickListener(new NavBarOnClickListener(2));
        diary.setOnClickListener(new NavBarOnClickListener(3));
        bodyStats.setOnClickListener(new NavBarOnClickListener(4));



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
        stats.setColorFilter(MainActivity.green);
        stats.setBackgroundColor(MainActivity.grey);
        // stats.setLayoutParams(para);

        bodyStats.setMinimumWidth(widths / 5);
        bodyStats.setMinimumHeight(160);
        bodyStats.setLayoutParams(layoutpara);
        bodyStats.setImageResource(R.drawable.weight_image);
        bodyStats.setColorFilter(MainActivity.green);
        bodyStats.setBackgroundColor(MainActivity.grey);

        diary.setMinimumWidth(widths / 5);
        diary.setMinimumHeight(160);
        diary.setImageResource(R.drawable.diary_image);
        diary.setColorFilter(MainActivity.green);
        diary.setBackgroundColor(MainActivity.grey);


        addWorkout.setMinimumWidth(widths / 5);
        addWorkout.setMinimumHeight(160);
        addWorkout.setLayoutParams(layoutpara);
        addWorkout.setImageResource(R.drawable.add_image);
        addWorkout.setColorFilter(MainActivity.green);
        addWorkout.setBackgroundColor(MainActivity.grey);

        profile.setMinimumWidth(widths / 5);
        profile.setMinimumHeight(160);
        profile.setLayoutParams(layoutpara);
        profile.setImageResource(R.drawable.tick_image);
        profile.setColorFilter(MainActivity.green);
        profile.setBackgroundColor(MainActivity.grey);





        if (caller == 1) {
            stats.setBackgroundColor(Color.rgb(46,52,56));
        } if (caller == 2) {
            addWorkout.setBackgroundColor(Color.rgb(46,52,56));
        } if (caller == 3) {
            diary.setBackgroundColor(Color.rgb(46,52,56));
        } if (caller == 4) {
            bodyStats.setBackgroundColor(Color.rgb(46,52,56));
        }

        layout.addView(stats);
        layout.addView(profile);
        layout.addView(addWorkout);
        layout.addView(diary);
        layout.addView(bodyStats);
    }

}

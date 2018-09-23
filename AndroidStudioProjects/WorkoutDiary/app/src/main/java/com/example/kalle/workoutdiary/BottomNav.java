package com.example.kalle.workoutdiary;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BottomNav {
    static LinearLayout addWorkout;
    static LinearLayout diary;
    static LinearLayout stats;
    static LinearLayout bodyStats;
    static LinearLayout profile;

    public static void makeBottomnavBar(LinearLayout layout, int widths, Context context, int caller) {


        addWorkout = new LinearLayout(context);
        diary = new LinearLayout(context);
        stats = new LinearLayout(context);
        bodyStats = new LinearLayout(context);
        profile = new LinearLayout(context);


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


        TextView statText = new TextView(context);
        statText.setText("Stats");
        statText.setTextColor(MainActivity.green);
        statText.setLayoutParams(layoutpara);
        statText.setTextSize(30);
        stats.addView(statText);
        stats.setMinimumWidth(widths / 5);
        stats.setOrientation(LinearLayout.VERTICAL);
        System.out.println("This is width: " + (widths / 4));
        stats.setMinimumHeight(160);
        // stats.setLayoutParams(para);

        TextView bodyText = new TextView(context);
        bodyText.setText("Body");
        bodyText.setTextSize(30);
        bodyText.setTextColor(MainActivity.green);
        bodyText.setLayoutParams(layoutpara);
        bodyStats.addView(bodyText);
        bodyStats.setMinimumWidth(widths / 5);
        bodyStats.setMinimumHeight(160);
        bodyStats.setOrientation(LinearLayout.VERTICAL);
        bodyStats.setLayoutParams(layoutpara);

        TextView diar = new TextView(context);
        diar.setText("Diary");
        diar.setTextSize(30);
        diary.addView(diar);
        diary.setMinimumWidth(widths / 5);
        diary.setMinimumHeight(160);
        diary.setOrientation(LinearLayout.VERTICAL);
        diar.setLayoutParams(layoutpara);
        //diary.setLayoutParams(layoutpara);
        diar.setTextColor(MainActivity.green);

        TextView addText = new TextView(context);
        addText.setText("+");
        addText.setTypeface(null, Typeface.BOLD);
        addText.setTextSize(45);
        addText.setTextColor(MainActivity.green);
        addText.setLayoutParams(layoutpara);
        addWorkout.addView(addText);
        addWorkout.setMinimumWidth(widths / 5);
        addWorkout.setMinimumHeight(160);
        addWorkout.setOrientation(LinearLayout.VERTICAL);
        addWorkout.setLayoutParams(layoutpara);

        TextView profText = new TextView(context);
        profText.setText("Profile");
        profText.setTextSize(30);
        profText.setTextColor(MainActivity.green);
        profText.setLayoutParams(layoutpara);
        profile.addView(profText);
        profile.setMinimumWidth(widths / 5);
        profile.setMinimumHeight(160);
        profile.setOrientation(LinearLayout.VERTICAL);
        profile.setLayoutParams(layoutpara);




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

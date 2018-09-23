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

    public static void makeBottomnavBar(LinearLayout layout, int widths, Context context, int caller) {


        addWorkout = new LinearLayout(context);
        diary = new LinearLayout(context);
        stats = new LinearLayout(context);
        stats.setOnClickListener(new NavBarOnClickListener(1));
        addWorkout.setOnClickListener(new NavBarOnClickListener(2));
        diary.setOnClickListener(new NavBarOnClickListener(3));


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
        stats.setMinimumWidth(widths / 3);
        stats.setOrientation(LinearLayout.VERTICAL);
        System.out.println("This is width: " + (widths / 3));
        stats.setMinimumHeight(160);
        // stats.setLayoutParams(para);

        TextView addText = new TextView(context);
        addText.setText("+");
        addText.setTypeface(null, Typeface.BOLD);
        addText.setTextSize(45);
        addText.setTextColor(MainActivity.green);
        addText.setLayoutParams(layoutpara);
        addWorkout.addView(addText);
        addWorkout.setMinimumWidth(widths / 3);
        addWorkout.setMinimumHeight(160);
        addWorkout.setOrientation(LinearLayout.VERTICAL);
        addWorkout.setLayoutParams(layoutpara);

        TextView diar = new TextView(context);
        diar.setText("Diary");
        diar.setTextSize(30);
        diary.addView(diar);
        diary.setMinimumWidth(widths / 3);
        diary.setMinimumHeight(160);
        diary.setOrientation(LinearLayout.VERTICAL);
        diar.setLayoutParams(layoutpara);
        //diary.setLayoutParams(layoutpara);
        diar.setTextColor(MainActivity.green);


        if (caller == 1) {
           stats.setBackgroundColor(Color.rgb(46,52,56));
        } if (caller == 2) {
           addWorkout.setBackgroundColor(Color.rgb(46,52,56));
        } if (caller == 3) {
            diary.setBackgroundColor(Color.rgb(46,52,56));
        }

        layout.addView(stats);
        layout.addView(addWorkout);
        layout.addView(diary);
    }
}

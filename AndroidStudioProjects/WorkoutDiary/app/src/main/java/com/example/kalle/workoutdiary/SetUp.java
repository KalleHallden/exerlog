package com.example.kalle.workoutdiary;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.graphics.Color.WHITE;

public class SetUp {

    public static void makeLabels(LinearLayout linear, Context context, int width) {

        int length = width /10;
        MyLinearLayout creatLinearLayout = new MyLinearLayout();

        //Make Labels:
        TextView nameLabel = new TextView(context);
        nameLabel.setText("Exercise");
        nameLabel.setPadding(length, 0, 0, 0);
        nameLabel.setTextSize(12);
        nameLabel.setTextColor(WHITE);

        TextView repsLabel = new TextView(context);
        repsLabel.setText("Reps");
        repsLabel.setPadding(length + 20, 0, 0, 0);
        repsLabel.setTextSize(12);
        repsLabel.setTextColor(WHITE);

        TextView setsLabel = new TextView(context);
        setsLabel.setText("Sets");
        setsLabel.setPadding(length, 0, 0, 0);
        setsLabel.setTextSize(12);
        setsLabel.setTextColor(WHITE);

        TextView weightLabel = new TextView(context);
        weightLabel.setText("Weight");
        weightLabel.setPadding(length - 30, 0, 0, 0);
        weightLabel.setTextSize(12);
        weightLabel.setTextColor(WHITE);

        TextView restLabel = new TextView(context);
        restLabel.setText("Rest");
        restLabel.setPadding(length, 0, 40, 0);
        restLabel.setTextSize(12);
        restLabel.setTextColor(WHITE);

        creatLinearLayout.setupLayout(linear);
        SetUp.makeLinearLayoutParams(linear);
        linear.addView(nameLabel);
        linear.addView(repsLabel);
        linear.addView(setsLabel);
        linear.addView(weightLabel);
        linear.addView(restLabel);

    }



    public static void makeLinearLayoutParamsTopBar(LinearLayout layout) {
        LinearLayout.LayoutParams layoutpara = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutpara.setMargins(0, 0, 0, 0);
        layoutpara.gravity = Gravity.CENTER;
        layout.setLayoutParams(layoutpara);
    }

    public static void makeLinearLayoutParams(LinearLayout layout) {
        LinearLayout.LayoutParams layoutpara = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutpara.setMargins(10, 10, 10, 10);
        layoutpara.gravity = Gravity.CENTER;
        layout.setLayoutParams(layoutpara);
    }

    public static void makeButtonAndTextRowParams(LinearLayout.LayoutParams params) {
        params.leftMargin = 10;
        params.rightMargin = 10;
        params.topMargin = 20;
        params.bottomMargin = 20;
        params.gravity = Gravity.CENTER_HORIZONTAL;
    }


}

package com.example.kalle.workoutdiary;

import android.widget.Button;

public class MyButton {

    public static void makeButton(Button butn, String color) {
        butn.setTextColor(BottomNaviClass.grey);
        butn.setTextSize(20);

        if (color.equals("green")) {
            butn.setBackgroundColor(BottomNaviClass.green);
        } if (color.equals("grey")) {
            butn.setBackgroundColor(BottomNaviClass.lightBlack);
        } if (color.equals("red")) {
            butn.setBackgroundColor(BottomNaviClass.red);
        }

    }
}
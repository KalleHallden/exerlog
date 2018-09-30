package com.example.kalle.workoutdiary;

import android.graphics.Typeface;
import android.widget.EditText;

public class MyTextField {

        public static void makeTextField(EditText texfield, int widths) {
            texfield.setBackgroundColor(BottomNaviClass.green);
            texfield.setTextColor(BottomNaviClass.grey);
            texfield.setTextSize(15);
            texfield.setTypeface(null, Typeface.BOLD);
            texfield.setMaxHeight(100);
            texfield.setMaxLines(3);
            texfield.setMaxWidth(widths);

        }
    }
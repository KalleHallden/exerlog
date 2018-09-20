package com.example.kalle.workoutdiary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.Display;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import android.graphics.Typeface;

import static android.text.Layout.Alignment.ALIGN_CENTER;

public class DiaryActivity extends AppCompatActivity {

    static int identify;

    int x;
    ScrollView rowScroller;
    static ArrayList<LinearLayout> rows;
    int y;
    private int rowClickedId;

    Diary diary;

    public void setRowClickedId(int row) {
        rowClickedId = row;
    }
    public int getRowClickedId() {
        return  rowClickedId;
    }

    public static void removeRow(int index) {
        rows.remove(index);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
        y++;
        if (y > 1) {
            recreate();
            y = 0;
        }

    }

    public void setUp() {
        rows = new ArrayList<LinearLayout>();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        LinearLayout rowsInsideScroll = new LinearLayout(this);
        rowsInsideScroll.setOrientation(LinearLayout.VERTICAL);


        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        makeButtonAndTextRowParams(buttonParams);

        rowScroller = new ScrollView(this);
        rowScroller.setLayoutParams(new RelativeLayout.LayoutParams(
                ScrollView.LayoutParams.FILL_PARENT, height - 300));
        rowScroller.setBackgroundColor(MainActivity.grey);
        System.out.println("this is numworkouts " + ((SaveWorkout.n) + 1));


        for (int i = 0; i < ((SaveWorkout.n) + 1); i++) {
            rows.add(new LinearLayout(getBaseContext()));
            LinearLayout rowCreated = rows.get(i);
            String theId = Integer.toString(i);
            rowCreated.setOnClickListener(new RowClickedOnClickListener(theId));
        }

        int counter = rows.size();

        while (counter != 0) {
            System.out.println("Row counter is at " + counter);

            LinearLayout rowCreated = rows.get(counter - 1);
            rowCreated.setBackgroundColor(MainActivity.green);
            rowCreated.setMinimumWidth(1000);
            rowCreated.setMinimumHeight(80);
            TextView name = new TextView(this);
            name.setText("Workout " + counter);
            name.setTypeface(null, Typeface.BOLD);
            name.setTextSize(15);
            name.setTextColor(MainActivity.grey);
            name.setPadding(20, 40, 0, 0);

            rowCreated.addView(name, buttonParams);

            rowsInsideScroll.addView(rowCreated, buttonParams);
            counter = counter -1;
        }

        LinearLayout.LayoutParams bottomPara = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, Gravity.BOTTOM);

        LinearLayout container = new LinearLayout(this);
        container.setLayoutParams(bottomPara);
        container.setOrientation(LinearLayout.VERTICAL);
        LinearLayout scrollContainer = new LinearLayout(this);

        BottomNav bottomNav = new BottomNav();

        LinearLayout bar = new LinearLayout(this);
        bottomNav.makeBottomnavBar(bar, width);

        bottomNav.stats.setOnClickListener(new NavBarOnClickListener(1));
        bottomNav.addWorkout.setOnClickListener(new NavBarOnClickListener(2));
        bottomNav.diary.setOnClickListener(new NavBarOnClickListener(3));

        rowScroller.addView(rowsInsideScroll);
        scrollContainer.addView(rowScroller);
        scrollContainer.setLayoutParams(bottomPara);
        //container.addView(rowsInsideScroll);
        //container.addView(bottomBar);
        container.addView(scrollContainer);
        container.addView(bar);
        container.setBackgroundColor(MainActivity.grey);
        setContentView(container, bottomPara);


    }

    class BottomNav {
        LinearLayout addWorkout = new LinearLayout(getBaseContext());
        LinearLayout diary = new LinearLayout(getBaseContext());
        LinearLayout stats = new LinearLayout(getBaseContext());

        public void makeBottomnavBar(LinearLayout layout, int widths) {
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.setMinimumWidth(widths);

            LinearLayout.LayoutParams layoutpara = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutpara.gravity = Gravity.CENTER;
            LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            para.gravity = Gravity.CENTER;


            TextView statText = new TextView(getBaseContext());
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

            TextView addText = new TextView(getBaseContext());
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

            TextView diar = new TextView(getBaseContext());
            diar.setText("Diary");
            diar.setTextSize(30);
            diary.addView(diar);
            diary.setMinimumWidth(widths / 3);
            diary.setMinimumHeight(160);
            diary.setOrientation(LinearLayout.VERTICAL);
            diary.setBackgroundColor(Color.rgb(46,52,56));
            diar.setLayoutParams(layoutpara);
            //diary.setLayoutParams(layoutpara);
            diar.setTextColor(MainActivity.green);

            layout.addView(stats);
            layout.addView(addWorkout);
            layout.addView(diary);
        }

    }

    class RowClickedOnClickListener implements View.OnClickListener {
        String id;

        public RowClickedOnClickListener(String name) {
            id = name;
        }

        public RowClickedOnClickListener() {
        }

        @Override
        public void onClick(View view) {

            System.out.println("Row " + id + " clicked");
            Context context = view.getContext();
            System.out.println("one");
            identify = Integer.parseInt(id);
            System.out.println("two");
            Intent i = new Intent(context, CopyOfWorkoutActivity.class);
            System.out.println("three");
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            System.out.println("four");
            //Intent intent = new Intent(context, BottomNavigation.class);
            context.startActivity(i);
            System.out.println("five");
        }


    }

    public void makeButtonAndTextRowParams(LinearLayout.LayoutParams params) {
        params.leftMargin = 10;
        params.rightMargin = 10;
        params.topMargin = 20;
        params.bottomMargin = 20;
        params.gravity = Gravity.CENTER_HORIZONTAL;
    }

}



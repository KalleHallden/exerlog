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
import android.view.Window;
import android.view.WindowManager;
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
    ScrollView rowScroller;
    static ArrayList<LinearLayout> rows;
    int y;
    int width;
    int height;

    View view;

    LinearLayout  rowsInsideScroll;
    LinearLayout container;
    LinearLayout bar;
    LinearLayout scrollContainer;
    LinearLayout.LayoutParams buttonParams;

    public static void removeRow(int index) {
        rows.remove(index);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();

    }

    public void setUp() {

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(MainActivity.black);




         Display display = getWindowManager().getDefaultDisplay();
         Point size = new Point();
         display.getSize(size);
         width = size.x;
         height = size.y;
         view = new View(this);

        buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        SetUp.makeButtonAndTextRowParams(buttonParams);

        makeLinears();
        makeRows();


        LinearLayout.LayoutParams bottomPara = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, Gravity.BOTTOM);

        container.setLayoutParams(bottomPara);

        rowScroller.addView(rowsInsideScroll);
        scrollContainer.addView(rowScroller);
        scrollContainer.setLayoutParams(bottomPara);
        container.addView(scrollContainer);
        container.addView(bar);
        container.setBackgroundColor(MainActivity.grey);
        setContentView(container, bottomPara);


    }


    public void makeLinears() {
        rows = new ArrayList<LinearLayout>();

        rowsInsideScroll = new LinearLayout(this);
        rowsInsideScroll.setOrientation(LinearLayout.VERTICAL);

        rowScroller = new ScrollView(this);
        rowScroller.setLayoutParams(new RelativeLayout.LayoutParams(
                ScrollView.LayoutParams.FILL_PARENT, height - 300));
        rowScroller.setBackgroundColor(MainActivity.grey);

        container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        scrollContainer = new LinearLayout(this);

        bar = new LinearLayout(this);
        BottomNav.makeBottomnavBar(bar, width, view.getContext(), 3);
    }


    public void makeRows() {
        for (int i = 0; i < ((SaveWorkout.n) + 1); i++) {
            rows.add(new LinearLayout(getBaseContext()));
            LinearLayout rowCreated = rows.get(i);
            String theId = Integer.toString(i);
            rowCreated.setOnClickListener(new RowClickedOnClickListener(theId));
        }
        setUpRows();
    }

    public void setUpRows() {
        int counter = SaveWorkout.n;

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
            System.out.println("add");
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

}



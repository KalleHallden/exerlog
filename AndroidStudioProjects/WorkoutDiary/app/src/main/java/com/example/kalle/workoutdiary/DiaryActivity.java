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

    int x;
    ScrollView rowScroller;
    ArrayList<LinearLayout> rows = new ArrayList<LinearLayout>();
    int y;
    private int rowClickedId;

    public void setRowClickedId(int row) {
        rowClickedId = row;
    }
    public int getRowClickedId() {
        return  rowClickedId;
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
    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    public void setUp() {
        buildit();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.rgb(43,43,43));
        LinearLayout rowsInsideScroll = new LinearLayout(this);
        rowsInsideScroll.setOrientation(LinearLayout.VERTICAL);


        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        makeButtonAndTextRowParams(buttonParams);

        rowScroller = new ScrollView(this);
        rowScroller.setLayoutParams(new RelativeLayout.LayoutParams(
                ScrollView.LayoutParams.FILL_PARENT, height));


        for (int i = 0; i < x; i++) {
            rows.add(new LinearLayout(getBaseContext()));
            LinearLayout rowCreated = rows.get(i);
            String theId = Integer.toString(i);
            rowCreated.setOnClickListener(new RowClickedOnClickListener(theId));
        }

        int counter = rows.size();

        while (counter != 0) {
            System.out.println("Row counter is at " + counter);

            LinearLayout rowCreated = rows.get(counter - 1);
            rowCreated.setBackgroundColor(Color.rgb(0,109,54));
            rowCreated.setMinimumWidth(1000);
            rowCreated.setMinimumHeight(80);
            TextView name = new TextView(this);
            name.setText("Workout " + counter);
            name.setTypeface(null, Typeface.BOLD);
            name.setTextSize(15);
            name.setTextColor(Color.rgb(43,43,43));
            name.setPadding(20, 40, 0, 0);

            rowCreated.addView(name, buttonParams);

            rowsInsideScroll.addView(rowCreated, buttonParams);
            counter = counter -1;
        }

        LinearLayout bottomBar = new LinearLayout(this);
        bottomBar.setBackgroundColor(Color.BLACK);
        bottomBar.setMinimumHeight(150);
        bottomBar.setMinimumWidth(width);
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
        setContentView(container, bottomPara);


    }

    class BottomNav {
        LinearLayout addWorkout = new LinearLayout(getBaseContext());
        LinearLayout diary = new LinearLayout(getBaseContext());
        LinearLayout stats = new LinearLayout(getBaseContext());

        public void makeBottomnavBar(LinearLayout layout, int widths) {
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.setMinimumWidth(widths);
            layout.setBackgroundColor(Color.BLACK);

            LinearLayout.LayoutParams layoutpara = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutpara.gravity = Gravity.CENTER;
            LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            para.gravity = Gravity.CENTER;


            TextView statText = new TextView(getBaseContext());
            statText.setText("Stats");
            statText.setTextColor(Color.rgb(0, 109, 54));
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
            addText.setTextColor(Color.rgb(0, 109, 54));
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
            diar.setLayoutParams(layoutpara);
            //diary.setLayoutParams(layoutpara);
            diar.setTextColor(Color.rgb(0, 149, 84));

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
            CopyOfWorkoutActivity builder = new CopyOfWorkoutActivity();
            builder.setWorkout(Integer.parseInt(id));
            Context context = view.getContext();
            Intent i = new Intent(context, CopyOfWorkoutActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Intent intent = new Intent(context, BottomNavigation.class);
            context.startActivity(i);
        }


    }

    public void makeButtonAndTextRowParams(LinearLayout.LayoutParams params) {
        params.leftMargin = 10;
        params.rightMargin = 10;
        params.topMargin = 20;
        params.bottomMargin = 20;
        params.gravity = Gravity.CENTER_HORIZONTAL;
    }



    public void buildit() {

        System.out.println("This is numWorkouts: " + x);
        int y = 1;
        for (int i = 0; i < y; i++) {
            String file1 = "Exercises" + (x);
            FileInputStream fis = null;
            try {
                fis = openFileInput(file1);
                System.out.println("We are still creating files");
                System.out.println("This is the current file you're creating: " + file1);
                x++;
                y++;
            } catch (FileNotFoundException e) {
                System.out.println("this is the end of files");
                e.printStackTrace();
            }
        }
    }




}

class WorkoutData {
    static int numberOfThisWorkout;
    ArrayList<Exercise> exercises = new ArrayList<Exercise>();
    private int volumeOfWorkout;

    public void setVolumeOfWorkout(int reps, int sets) {
        volumeOfWorkout = reps * sets;
    }
    public int getVolumeOfWorkout() {
        return  volumeOfWorkout;
    }

}



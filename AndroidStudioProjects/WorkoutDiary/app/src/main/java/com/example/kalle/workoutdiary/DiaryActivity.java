package com.example.kalle.workoutdiary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
        LinearLayout layout = new LinearLayout(this);
        setContentView(layout);
        layout.setBackgroundColor(Color.rgb(43,43,43));
        LinearLayout rowsInsideScroll = new LinearLayout(this);
        rowsInsideScroll.setOrientation(LinearLayout.VERTICAL);


        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        makeButtonAndTextRowParams(buttonParams);

        rowScroller = new ScrollView(this);
        rowScroller.setLayoutParams(new RelativeLayout.LayoutParams(
                ScrollView.LayoutParams.FILL_PARENT, 900));


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
            rowCreated.setBackgroundColor(Color.BLUE);
            rowCreated.setMinimumWidth(1000);
            rowCreated.setMinimumHeight(80);
            TextView name = new TextView(this);
            name.setText("Workout " + counter);
            name.setTextSize(10);
            rowCreated.addView(name, buttonParams);

            rowsInsideScroll.addView(rowCreated, buttonParams);
            counter = counter -1;
        }





        rowScroller.addView(rowsInsideScroll);
        layout.addView(rowScroller);


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



package com.example.kalle.workoutdiary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {

    int x;
    HorizontalScrollView rowScroller;
    ArrayList<LinearLayout> rows = new ArrayList<LinearLayout>();
    int y;
    private int rowClickedId;


    ArrayList<Workouts> workoutList = new ArrayList<Workouts>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
    }



    public void setUp() {



        buildit();
        //buildWorkouts();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int heighty = size.y;

        LinearLayout layout = new LinearLayout(this);
        setContentView(layout);
        layout.setBackgroundColor(Color.rgb(43,43,43));
        LinearLayout rowsInsideScroll = new LinearLayout(this);
        rowsInsideScroll.setOrientation(LinearLayout.HORIZONTAL);


        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        makeButtonAndTextRowParams(buttonParams);

        rowScroller = new HorizontalScrollView(this);
        RelativeLayout.LayoutParams lay = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 900);

        rowScroller.setLayoutParams(new RelativeLayout.LayoutParams(
                ScrollView.LayoutParams.FILL_PARENT, heighty));




        for (int i = 0; i < x; i++) {
            rows.add(new LinearLayout(getBaseContext()));
            LinearLayout rowCreated = rows.get(i);
            String theId = Integer.toString(i);
            rowCreated.setOnClickListener(new BarClickedOnClickListener(theId));
        }

        int counter = rows.size();

        while (counter != 0) {
            System.out.println("Row counter is at " + counter);

            LinearLayout rowCreated = rows.get(counter - 1);
            rowCreated.setBackgroundColor(Color.BLUE);
            rowCreated.setMinimumWidth(50);

            rowCreated.setMinimumHeight(counter *10);

            rowsInsideScroll.addView(rowCreated, buttonParams);

            counter = counter -1;
        }



        LinearLayout bottomBar = new LinearLayout(this);
        bottomBar.setBackgroundColor(Color.BLACK);
        bottomBar.setMinimumHeight(100);
        bottomBar.setMinimumWidth(width);

        LinearLayout containerAllViews = new LinearLayout(this);
        containerAllViews.setMinimumWidth(width);
        containerAllViews.setMinimumHeight(heighty);
        containerAllViews.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams bottomPara = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, Gravity.BOTTOM);

        rowScroller.addView(rowsInsideScroll);
        containerAllViews.addView(rowScroller, bottomPara);
        containerAllViews.addView(bottomBar);




        layout.addView(containerAllViews, bottomPara);



    }

    private int height;

    public void setHeight(int volume) {
        height = volume;
    }

    public int getHeight() {
        return height;
    }

    public void createHeight() {

    }

class BarClickedOnClickListener implements View.OnClickListener {
    String id;

    public BarClickedOnClickListener(String name) {
        id = name;
    }

    public BarClickedOnClickListener() {
    }

    @Override
    public void onClick(View view) {
        System.out.println("Row " + id + " clicked");
        CopyOfWorkoutActivity builder = new CopyOfWorkoutActivity();
        builder.setWorkout(Integer.parseInt(id));
    }


}

    public void makeButtonAndTextRowParams(LinearLayout.LayoutParams params) {
        params.leftMargin = 10;
        params.rightMargin = 10;
        params.topMargin = 20;
        params.bottomMargin = 20;
        params.gravity = Gravity.BOTTOM;
    }



    public void buildit() {

        System.out.println("This is numWorkouts: " + x);
        String end = " ";
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
                end = "END";
            }

            if (!end.equals("END")) {
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(isr);


                try {
                    StringBuffer sb = new StringBuffer();
                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line);
                        String text = (String) line;
                        System.out.println("This is the total lines int this new one: " + text);

                        String[] data = text.split("END");

                        workoutList.add(new Workouts());
                        Workouts thisWorkout = workoutList.get(i);
                        thisWorkout.savedExercisez = new ArrayList<Exercise>();

                        for (int d = 0; d < data.length; d++) {
                            System.out.println("This is current: " + data[d]);
                            String dat = data[d];
                            String[] info = dat.split("SPLIT");



                            thisWorkout.savedExercisez.add(new Exercise());
                            Exercise exer = thisWorkout.savedExercisez.get(d);

                            for (int o = 0; o < 5; o++) {
                                if (o == 0) {
                                    try {
                                        exer.setName(info[o]);
                                    } catch (Exception e) {
                                        exer.setName(" ");
                                    }
                                    System.out.println("This was your name: " + exer.getName());
                                } if (o == 1) {
                                    try {
                                        exer.setReps(info[o]);
                                    } catch (Exception e) {
                                        exer.setReps("0");
                                    }
                                    System.out.println("This was your reps: " + exer.getReps());
                                }
                                if (o == 2) {
                                    try {
                                        exer.setSets(info[o]);
                                    } catch (Exception e) {
                                        exer.setSets("1");
                                    }
                                    System.out.println("This was your sets: " + exer.getSets());
                                } if (o == 3) {
                                    try {
                                        exer.setWeight(info[o]);
                                    } catch (Exception e) {
                                        exer.setWeight("0");
                                    }
                                    System.out.println("This was your weight: " + exer.getWeight());
                                } if (o == 4) {
                                    try {
                                        exer.setRest(info[o]);
                                    } catch (Exception e) {
                                        exer.setRest("0");
                                    }
                                    System.out.println("This was your rest: " + exer.getRest());
                                }


                            }

                        }


                    }

                    System.out.println("");
                    isr.close();
                    System.out.println("");
                } catch (IOException e) {
                    System.out.println("Exception");

                }
            }



        }
    }

    public void buildWorkouts() {

        System.out.println("This is numWorkouts: " + x);

        System.out.println("You are currently creating this workout: " + "Exercises" + x);

        int y = 0;
        for (int i = 0; i < x; i++) {
            String file1 = "Exercises" + (i);
            FileInputStream fis = null;
            try {
                fis = openFileInput(file1);
                y++;
            } catch (FileNotFoundException e) {
                System.out.println("this is the bloody exception");
                e.printStackTrace();
            }
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            try {
                StringBuffer sb = new StringBuffer();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                    String text = (String) line;
                    System.out.println("This is the total lines int this new one: " + text);

                    String[] data = text.split("END");

                    workoutList.add(new Workouts());
                    Workouts thisWorkout = workoutList.get(i);
                    thisWorkout.savedExercisez = new ArrayList<Exercise>();

                    for (int d = 0; d < data.length; d++) {
                        System.out.println("This is current: " + data[d]);
                        String dat = data[d];
                        String[] info = dat.split("SPLIT");



                        thisWorkout.savedExercisez.add(new Exercise());
                        Exercise exer = thisWorkout.savedExercisez.get(d);

                        for (int o = 0; o < 5; o++) {
                            if (o == 0) {
                                try {
                                    exer.setName(info[o]);
                                } catch (Exception e) {
                                    exer.setName(" ");
                                }
                                System.out.println("This was your name: " + exer.getName());
                            } if (o == 1) {
                                try {
                                    exer.setReps(info[o]);
                                } catch (Exception e) {
                                    exer.setReps("0");
                                }
                                System.out.println("This was your reps: " + exer.getReps());
                            }
                            if (o == 2) {
                                try {
                                    exer.setSets(info[o]);
                                } catch (Exception e) {
                                    exer.setSets("1");
                                }
                                System.out.println("This was your sets: " + exer.getSets());
                            } if (o == 3) {
                                try {
                                    exer.setWeight(info[o]);
                                } catch (Exception e) {
                                    exer.setWeight("0");
                                }
                                System.out.println("This was your weight: " + exer.getWeight());
                            } if (o == 4) {
                                try {
                                    exer.setRest(info[o]);
                                } catch (Exception e) {
                                    exer.setRest("0");
                                }
                                System.out.println("This was your rest: " + exer.getRest());
                            }


                        }

                    }


                }

                System.out.println("");
                isr.close();
                System.out.println("");
            } catch (IOException e) {
                System.out.println("Exception");

            }
        }


    }





}

class Workouts {
    ArrayList<Exercise> savedExercisez;
    int volume;
}
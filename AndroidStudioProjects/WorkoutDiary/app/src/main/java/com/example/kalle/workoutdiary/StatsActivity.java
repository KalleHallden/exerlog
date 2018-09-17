package com.example.kalle.workoutdiary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.util.List;
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
    int clicked;
    Button barButton;


    ArrayList<Workouts> workoutList;

    LinearLayout rowsInsideScroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("We got through");
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
        rowsInsideScroll = new LinearLayout(this);
        rowsInsideScroll.setOrientation(LinearLayout.HORIZONTAL);


        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        makeButtonAndTextRowParams(buttonParams);

        rowScroller = new HorizontalScrollView(this);
        RelativeLayout.LayoutParams lay = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 900);

        rowScroller.setLayoutParams(new RelativeLayout.LayoutParams(
                ScrollView.LayoutParams.FILL_PARENT, heighty));


         int counter = x;
         System.out.println("Size "+counter);
         System.out.println("Workout list size " + workoutList.size());

        for (int i = 0; i < x; i++) {
            rows.add(new LinearLayout(getBaseContext()));
            LinearLayout rowCreated = rows.get(i);
            String theId = Integer.toString(i);
            rowCreated.setOnClickListener(new BarClickedOnClickListener(theId));
        }

        while (counter != 0) {
            System.out.println("Row counter is at " + counter);
            Workouts work = workoutList.get(counter -1);
            LinearLayout rowCreated = rows.get(counter - 1);
            rowCreated.setBackgroundColor(Color.rgb(0,109,54));
            rowCreated.setMinimumWidth(50);

            System.out.println("This is the height: " + work.getVolume());

            rowCreated.setMinimumHeight(work.getVolume());

            rowsInsideScroll.addView(rowCreated, buttonParams);

            counter = counter -1;
        }



        LinearLayout bottomBar = new LinearLayout(this);
        bottomBar.setBackgroundColor(Color.BLACK);
        bottomBar.setMinimumHeight(150);
        bottomBar.setMinimumWidth(width);

        LinearLayout containerAllViews = new LinearLayout(this);
        containerAllViews.setMinimumWidth(width);
        containerAllViews.setMinimumHeight(heighty);
        containerAllViews.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams bottomPara = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, Gravity.BOTTOM);

        BottomNav bottomNav = new BottomNav();

        LinearLayout bar = new LinearLayout(this);
        bottomNav.makeBottomnavBar(bar, width);


        bottomNav.stats.setOnClickListener(new NavBarOnClickListener(1));
        bottomNav.addWorkout.setOnClickListener(new NavBarOnClickListener(2));
        bottomNav.diary.setOnClickListener(new NavBarOnClickListener(3));

        LinearLayout buttonBar = new LinearLayout(this);
        buttonBar.setMinimumWidth(width);
        buttonBar.setMinimumHeight(150);


        barButton = new Button(this);
        barButton.setBackgroundColor(Color.rgb(30,30,30));
        barButton.setWidth(width);
        barButton.setHeight(120);
        barButton.setText("Day View");
        barButton.setTextSize(20);
        barButton.setTextColor(Color.rgb(0,109,54));
        barButton.setOnClickListener(new ChangeBarOnClickListener());



        buttonBar.addView(barButton);
        rowScroller.addView(rowsInsideScroll);
        containerAllViews.addView(buttonBar);
        containerAllViews.addView(rowScroller, bottomPara);
        containerAllViews.addView(bar);




        layout.addView(containerAllViews, bottomPara);



    }
    List<Integer> barHeights;
    class ChangeBarOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            
            
            LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            makeButtonAndTextRowParams(buttonParams);
            System.out.println("Clicked " + clicked);
            rowsInsideScroll.removeAllViews();

            if (clicked == 0) {
                System.out.println("We are in");
                clicked++;
                System.out.println("clicked2 " + clicked);
                setRows(7);
                barButton.setText("Week View");

                int counter2 = barHeights.size();

                while (counter2 != 0) {
                    System.out.println("Row counter is at " + counter2);
                    Workouts work = workoutList.get(counter2 -1);
                    LinearLayout rowCreated = rows.get(counter2 - 1);
                    rowCreated.setBackgroundColor(Color.rgb(0,109,54));
                    rowCreated.setMinimumWidth(50);

                    System.out.println("This is the height: " + work.getVolume());

                    rowCreated.setMinimumHeight(work.getVolume());

                    rowsInsideScroll.addView(rowCreated, buttonParams);

                    counter2 = counter2 -1;
                }

            } else if (clicked == 1) {
                barButton.setText("Month View");
                clicked++;
                System.out.println(clicked);
                setRows(30);
                int counter2 = barHeights.size();

                while (counter2 != 0) {
                    System.out.println("Row counter is at " + counter2);
                    Workouts work = workoutList.get(counter2 -1);
                    LinearLayout rowCreated = rows.get(counter2 - 1);
                    rowCreated.setBackgroundColor(Color.rgb(0,109,54));
                    rowCreated.setMinimumWidth(50);

                    System.out.println("This is the height: " + work.getVolume());

                    rowCreated.setMinimumHeight(work.getVolume());

                    rowsInsideScroll.addView(rowCreated, buttonParams);

                    counter2 = counter2 -1;
                }                                                                            

            } else if (clicked == 2) {
                barButton.setText("Day View");
                clicked = 0;
                int counter = workoutList.size();
                for (int i = 0; i < x; i++) {                                                 
                    rows.add(new LinearLayout(getBaseContext()));                             
                    LinearLayout rowCreated = rows.get(i);                                    
                    String theId = Integer.toString(i);                                       
                    rowCreated.setOnClickListener(new BarClickedOnClickListener(theId));      
                }                                                                             
                while (counter != 0) {
                    System.out.println("Row counter is at " + counter);
                    Workouts work = workoutList.get(counter -1);
                    LinearLayout rowCreated = rows.get(counter - 1);
                    rowCreated.setBackgroundColor(Color.rgb(0,109,54));
                    rowCreated.setMinimumWidth(50);

                    System.out.println("This is the height: " + work.getVolume());

                    rowCreated.setMinimumHeight(work.getVolume());

                    rowsInsideScroll.addView(rowCreated, buttonParams);

                    counter = counter -1;
                }
            }
        }
    }
    int weekDays = 7;


    public void setRows(int weekOrMonth) {
        int vol = 0;
        int rowCount;
        rows.clear();
        if (weekOrMonth == 7) {
            rowCount = 0;
            System.out.println("building");
            // try to sum up each seven days of workoutvolumes and set each bar to be that tall
            System.out.println("building workouts " + x);
            barHeights = new ArrayList<>();
            int a = 0;
            for (int i = 0; i < x; i++) {
                if (a == 6) {
                    rows.add(new LinearLayout(getBaseContext()));
                    LinearLayout rowCreated = rows.get(rowCount);
                    String theId = Integer.toString(rowCount);
                    rowCreated.setOnClickListener(new BarClickedOnClickListener(theId));
                    barHeights.add(vol);
                    System.out.println(vol);
                    vol = 0;
                    a = 0;
                    rowCount++;
                } else {
                    Workouts work = workoutList.get(i);
                    vol = vol + work.getVolume();
                    a++;
                }
            }
        }
        if (weekOrMonth == 30) {
            rowCount = 0;
            // try to sum up every 30 workouts worth of volumes and set each bar to be that tall
            barHeights = new ArrayList<>();
            int a = 0;
            if (workoutList.size() >= 29) {
            for (int i = 0; i < x; i++) {
                if (a == 29) {
                    rows.add(new LinearLayout(getBaseContext()));
                    LinearLayout rowCreated = rows.get(rowCount);
                    String theId = Integer.toString(rowCount);
                    rowCreated.setOnClickListener(new BarClickedOnClickListener(theId));
                    barHeights.add(vol);
                    vol = 0;
                    a = 0;
                } else {
                    Workouts work = workoutList.get(i);
                    vol = vol + work.getVolume();
                    a++;
                }
            }
        }

    } else {
            // Print a messege to the user "Nothing to show here yet"
            System.out.println("Not enough workouts yet: " + workoutList.size());
        }
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
            statText.setTextColor(Color.rgb(0, 149, 84));
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
            diar.setTextColor(Color.rgb(0, 109, 54));

            layout.addView(stats);
            layout.addView(addWorkout);
            layout.addView(diary);
        }

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

int g;

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
        //CopyOfWorkoutActivity builder = new CopyOfWorkoutActivity();
        //builder.setWorkout(Integer.parseInt(id));
        int row = Integer.parseInt(id);
        LinearLayout rowCreated = rows.get(row);
        rowCreated.setBackgroundColor(Color.rgb(0,80,34));
            if (g != row) {
                System.out.println("Number: " + g);
                LinearLayout rowz = rows.get(g);
                rowz.setBackgroundColor(Color.rgb(0,109,54));
                g = row;
            } else {
                System.out.println("Number: " + g);
                g = row;
            }

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

    workoutList = new ArrayList<Workouts>();

        System.out.println("This is numWorkouts: " + (x));
        //if (x != 0) {
          //  x = x - 1;
        //}

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

                            thisWorkout.setVolume(exer.getReps(), exer.getSets());
                            System.out.println("Volume: " + thisWorkout.getVolume());


                        }


                    }
                    System.out.println("this is how many workouts have been created " + workoutList.size());

                    System.out.println("");
                    isr.close();
                    System.out.println("");
                } catch (IOException e) {
                    System.out.println("Exception");

                }
            }



        }
    }

}

class Workouts {
    ArrayList<Exercise> savedExercisez;
    private int volume;

    public void setVolume(int reps, int sets) {
        volume = volume + (reps*sets);
    }

    public int getVolume() {
        return volume;
    }
}
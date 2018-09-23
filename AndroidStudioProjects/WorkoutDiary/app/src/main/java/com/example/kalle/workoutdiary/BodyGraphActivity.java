package com.example.kalle.workoutdiary;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

public class BodyGraphActivity extends AppCompatActivity {

    int x;
    HorizontalScrollView rowScroller;
    ArrayList<LinearLayout> rows = new ArrayList<LinearLayout>();
    int y;
    private int rowClickedId;
    int clicked;
    Button barButton;

    ArrayList<BodyStats> bodystatList;

    LinearLayout rowsInsideScroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("We got through");
        setUp();
    }



    public void setUp() {

        View view = new View(this);
        SaveWorkout.checkExistingFiles(view.getContext());

        buildit();
        //buildWorkouts();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int heighty = size.y;

        LinearLayout layout = new LinearLayout(this);
        setContentView(layout);
        layout.setBackgroundColor(MainActivity.grey);
        rowsInsideScroll = new LinearLayout(this);
        rowsInsideScroll.setOrientation(LinearLayout.HORIZONTAL);


        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        makeButtonAndTextRowParams(buttonParams);

        rowScroller = new HorizontalScrollView(this);
        RelativeLayout.LayoutParams lay = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 900);

        rowScroller.setLayoutParams(new RelativeLayout.LayoutParams(
                ScrollView.LayoutParams.FILL_PARENT, heighty));


        int counter = bodystatList.size();
        System.out.println("Size "+ counter);
        System.out.println("Workout list size " + bodystatList.size());

        for (int i = 0; i < counter; i++) {
            rows.add(new LinearLayout(getBaseContext()));
            LinearLayout rowCreated = rows.get(i);
            String theId = Integer.toString(i);
            rowCreated.setOnClickListener(new BarClickedOnClickListener(theId));
        }

        while (counter != 0) {
            System.out.println("Row counter is at " + counter);
            BodyStats work = bodystatList.get(counter - 1);
            LinearLayout rowCreated = rows.get(counter - 1);
            rowCreated.setBackgroundColor(MainActivity.green);
            rowCreated.setMinimumWidth(50);

            System.out.println("This is the height: " + SaveWorkout.volumes[counter - 1]);

            rowCreated.setMinimumHeight((work.getBodyWeight().intValue()));

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

        LinearLayout bar = new LinearLayout(this);
        BottomNav.makeBottomnavBar(bar, width, view.getContext(), 1);

        LinearLayout buttonBar = new LinearLayout(this);
        buttonBar.setMinimumWidth(width);
        buttonBar.setMinimumHeight(150);


        barButton = new Button(this);
        barButton.setBackgroundColor(Color.rgb(30,30,30));
        barButton.setWidth(width);
        barButton.setHeight(120);
        barButton.setText("Day View");
        barButton.setTextSize(20);
        barButton.setTextColor(MainActivity.green);
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
                    BodyStats work = bodystatList.get(counter2 -1);
                    LinearLayout rowCreated = rows.get(counter2 - 1);
                    rowCreated.setBackgroundColor(MainActivity.green);
                    rowCreated.setMinimumWidth(50);

                    System.out.println("This is the height: " + work.getBodyWeight());

                    rowCreated.setMinimumHeight(barHeights.get(counter2-1));

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
                    BodyStats work = bodystatList.get(counter2 -1);
                    LinearLayout rowCreated = rows.get(counter2 - 1);
                    rowCreated.setBackgroundColor(MainActivity.green);
                    rowCreated.setMinimumWidth(50);

                    System.out.println("This is the height: " + work.getBodyWeight());

                    rowCreated.setMinimumHeight(barHeights.get(counter2 - 1));

                    rowsInsideScroll.addView(rowCreated, buttonParams);

                    counter2 = counter2 -1;
                }

            } else if (clicked == 2) {
                barButton.setText("Day View");
                clicked = 0;
                int counter = bodystatList.size();
                System.out.println();
                for (int i = 0; i < counter; i++) {
                    rows.add(new LinearLayout(getBaseContext()));
                    LinearLayout rowCreated = rows.get(i);
                    String theId = Integer.toString(i);
                    rowCreated.setOnClickListener(new BarClickedOnClickListener(theId));
                }
                while (counter != 0) {
                    System.out.println("Row counter is at " + counter);
                    LinearLayout rowCreated = rows.get(counter - 1);
                    rowCreated.setBackgroundColor(MainActivity.green);
                    rowCreated.setMinimumWidth(50);

                    System.out.println("This is the height: " );

                    rowCreated.setMinimumHeight((bodystatList.get(counter- 1).getBodyWeight().intValue()));

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
            System.out.println("building workouts " + SaveWorkout.n);
            barHeights = new ArrayList<>();
            int a = 0;
            for (int i = 0; i < SaveWorkout.n; i++) {
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
                    vol = vol + SaveWorkout.volumes[i];
                    a++;
                }
            }
        }
        if (weekOrMonth == 30) {
            rowCount = 0;
            // try to sum up every 30 workouts worth of volumes and set each bar to be that tall
            barHeights = new ArrayList<>();
            int a = 0;
            if (bodystatList.size() >= 29) {
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
                        vol = vol + SaveWorkout.volumes[i];
                        a++;
                    }
                }
            }

        } else {
            // Print a messege to the user "Nothing to show here yet"
            System.out.println("Not enough workouts yet: " + bodystatList.size());
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
                rowz.setBackgroundColor(MainActivity.green);
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

    public ArrayList<BodyStats> buildit() {
        View view = new View(getBaseContext());
        bodystatList = new ArrayList<BodyStats>();
        SaveBodyWeight opener = new SaveBodyWeight();

        for (int i = 0; i < SaveBodyWeight.n; i++) {
            bodystatList.add(new BodyStats());
            BodyStats bodystats = bodystatList.get(i);
            try {
                bodystats = opener.openBodyStat(i, view.getContext());
                System.out.println("Bodystat built: " + i);
            } catch (Exception e) {
                System.out.println("No bodystat found");
                System.out.println(bodystatList.size());
            }
        }
        return bodystatList;
    }

}
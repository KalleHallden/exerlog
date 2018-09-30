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
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class StatChooserActivity extends AppCompatActivity {

    static int identify;
    static ArrayList<LinearLayout> rows;
    int width;
    int height;

    View view;

    LinearLayout container;
    LinearLayout buttonRows;
    LinearLayout bar;
    LinearLayout.LayoutParams buttonParams;

    Button workoutStatButton;
    Button bodyStatButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();

    }

    public void setUp() {

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(BottomNaviClass.black);




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


        LinearLayout.LayoutParams bottomPara = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, Gravity.BOTTOM);



        container.addView(buttonRows, bottomPara);
        container.addView(bar);
        container.setBackgroundColor(BottomNaviClass.grey);
        setContentView(container, bottomPara);


    }


    public void makeLinears() {

        buttonRows = new LinearLayout(getBaseContext());
        buttonRows.setLayoutParams(buttonParams);
        buttonRows.setOrientation(LinearLayout.VERTICAL);
        addButtons();

        container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);

        bar = new LinearLayout(this);
        BottomNav.makeBottomnavBar(bar, width, view.getContext(), 1);
    }
    public void addButtons() {

        workoutStatButton = new Button(getBaseContext());
        workoutStatButton.setText("Workout Stats");
        workoutStatButton.setOnClickListener(new RowClickedOnClickListener("1"));

        bodyStatButton = new Button(getBaseContext());
        bodyStatButton.setText("Body Stats");
        bodyStatButton.setOnClickListener(new RowClickedOnClickListener("2"));



        buttonBuilder(workoutStatButton);
        buttonBuilder(bodyStatButton);
        buttonRows.addView(workoutStatButton);
        buttonRows.addView(bodyStatButton);
    }

    public void buttonBuilder(Button button) {
        MyButton.makeButton(button, "green");
        button.setMinimumWidth(width - 40);
        button.setMinimumHeight(height / 3);
        button.setLayoutParams(buttonParams);
        button.setTextSize(40);
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

            System.out.println("two");
            Intent i = null;

            if (id.equals("1")) {
                i = new Intent(context, StatsActivity.class);
                System.out.println("three");
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                System.out.println("four");
            } if (id.equals("2")) {
                 i = new Intent(context, BodyGraphActivity.class);
                System.out.println("three");
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                System.out.println("four");
            }

                context.startActivity(i);
           

            System.out.println("five");
        }


    }

}

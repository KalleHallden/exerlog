package com.example.kalle.workoutdiary;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.ImageDecoder;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Button;
import java.io.*;
import android.content.Context;

import java.nio.file.Paths;
import java.util.Scanner;
import java.io.File;

import java.io.IOException;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.view.Display;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Toolbar;
import android.content.Intent;

import java.util.ArrayList;

import static android.graphics.Color.*;
import static android.view.View.TEXT_ALIGNMENT_CENTER;

public class MainActivity extends AppCompatActivity {

    static int green = Color.rgb(4,168,46);
    static int grey = Color.rgb(56,62,66);

    Button addExerciseButton;
    Button saveButton;
    Button one;
    Button startNewWorkoutButton;
    LinearLayout row;
    static LinearLayout exerciseRowContainer;
    ScrollView rowScroller;
    static TextView volumeLabel;
    static ArrayList<LinearLayout> rowList;
    ArrayList<Exercise> exerciseList;
    static ArrayList<ArrayList> containerFortestxArray;
    static ArrayList<EditText> textFieldArray;
    static Workout thisWorkout;
    static int numberOfExercises;
    static int vol;
    Diary diary;


    static int numWorkouts;

    private String fileNameExercises = "";
    private String fileNameWorkout = "";

    public String getFileNameExercises() {
        return fileNameExercises;
    }

    public String getFileNameWorkout() {
        return fileNameWorkout;
    }

    public void setFileNameExercises(String s) {
        fileNameExercises = s;
    }

    public void setFileNameWorkout(String w) {
        fileNameWorkout = w;
    }

    public ArrayList<Exercise> savedExercises = new ArrayList<Exercise>();
    ArrayList<Workout> savedWorkouts = new ArrayList<Workout>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();


    }

    public void setUp() {

        View view = new View(this);
        diary = new Diary();
        diary.openWorkouts(view.getContext());
        SaveWorkout.checkExistingFiles(view.getContext());
        //thisWorkout.savedExercisez.clear();
        //diary.openSpecificWorkout(view, 0);
        thisWorkout = new Workout();



        numberOfExercises = 0;
        exerciseList = new ArrayList<Exercise>();
        containerFortestxArray = new ArrayList<ArrayList>();
        rowList = new ArrayList<LinearLayout>();










        String wok = "Workout" + numWorkouts;
        String exs = "Exercises" + numWorkouts;
        setFileNameExercises(exs);
        setFileNameWorkout(wok);
        numWorkouts++;


        savedWorkouts.add(new Workout());
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        int thirdWidth = (int) (width * 0.333333);

        LinearLayout testLayout = new LinearLayout(this);
        testLayout.setBackgroundColor(grey);
        setContentView(testLayout);

        LinearLayout totalLayout = new LinearLayout(this);
        totalLayout.setOrientation(LinearLayout.VERTICAL);


        //Make rows for labels, exercise rows and button
        row = new LinearLayout(this);
        row.setOrientation(LinearLayout.VERTICAL);

        // makeLinearLayoutParams(row);


        LinearLayout topBar = new LinearLayout(this);
        topBar.setBackgroundColor(Color.rgb(30, 30, 30));

        LinearLayout layoutTop = new LinearLayout(this);
        //layoutTop.setBackgroundColor(Color.BLUE);

        LinearLayout layoutTop2 = new LinearLayout(this);
        // layoutTop2.setBackgroundColor(Color.GREEN);
        LinearLayout layoutTop3 = new LinearLayout(this);
        //layoutTop3.setBackgroundColor(Color.WHITE);


        int widths = topBar.getWidth();

        layoutTop2.setMinimumHeight(100);
        layoutTop2.setMinimumWidth(thirdWidth);
        layoutTop3.setMinimumHeight(100);
        layoutTop3.setMinimumWidth(thirdWidth);
        layoutTop.setMinimumHeight(100);
        layoutTop.setMinimumWidth(thirdWidth);

        saveButton = new Button(this);
        startNewWorkoutButton = new Button(this);
        one = new Button(this);
        one.setOnClickListener(new AddRowOnClickListener());
        one.setText("+");
        one.setTextSize(30);
        one.setTextColor(green);
        one.setBackgroundColor(Color.rgb(30,30,30));
        one.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);

        ImageView tick = new ImageView(this);
        tick.setImageResource(R.mipmap.ticks);

        layoutTop.addView(saveButton);
        layoutTop3.addView(one);

        //saveButton.setBackgroundResource(R.mipmap.ticks);
        saveButton.setText("✓");
        saveButton.setTextSize(30);
        saveButton.setTextColor(green);
        saveButton.setBackgroundColor(topBar.getSolidColor());
        saveButton.setOnClickListener(new SaveWorkoutListener());

        GridLayout grid = new GridLayout(this);
        //grid.setBackgroundColor(Color.RED);
        grid.setOrientation(GridLayout.HORIZONTAL);
        grid.addView(layoutTop);
        grid.addView(layoutTop2);
        grid.addView(layoutTop3);

        topBar.addView(grid);
        topBar.setMinimumHeight(100);


        // Make layout for labels
        LinearLayout labelrow = new LinearLayout(this);
        makeLabels(labelrow);

        volumeLabel = new TextView(this);
        volumeLabel.setText("Total Volume: 0");
        volumeLabel.setTextColor(WHITE);
        LinearLayout volumeRow = new LinearLayout(this);


        // Make container for Exercise rows
        exerciseRowContainer = new LinearLayout(this);
        exerciseRowContainer.setOrientation(LinearLayout.VERTICAL);
        rowScroller = new ScrollView(this);
        rowScroller.setLayoutParams(new RelativeLayout.LayoutParams(
                ScrollView.LayoutParams.FILL_PARENT, 800));

       // AddRowOnClickListener addRow = new AddRowOnClickListener();
        // make save button
        MyButton makbtn = new MyButton();
        addExerciseButton = new Button(this);
        makbtn.makeButton(addExerciseButton);
        addExerciseButton.setText("Add");
        addExerciseButton.setOnClickListener(new Adder());

        // Button Parameter creator
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        makeButtonAndTextRowParams(buttonParams);

        // Make initial row of textfields and increment number of rows by 1 and add it to the exerciseRowContainer
        rowList.add(new LinearLayout(this));
        LinearLayout textFieldRows = rowList.get(numberOfExercises);
        TextFieldMaker.makeTextFields(textFieldRows, view);
        exerciseRowContainer.addView(textFieldRows, buttonParams);
        numberOfExercises = numberOfExercises + 1;
        rowScroller.addView(exerciseRowContainer);

        makeLinearLayoutParams(volumeRow);
        volumeRow.addView(volumeLabel);
        makeLinearLayoutParamsTopBar(totalLayout);

        LinearLayout bottomBar = new LinearLayout(this);
        LinearLayout divider = new LinearLayout(this);
        bottomBar.setBackgroundColor(Color.BLACK);
        bottomBar.setMinimumHeight(150);
        bottomBar.setMinimumWidth(width);
        LinearLayout.LayoutParams bottomPara = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, Gravity.BOTTOM);
        LinearLayout.LayoutParams topPara = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, Gravity.TOP);

        BottomNav bottomNav = new BottomNav();

        LinearLayout bar = new LinearLayout(this);
        bottomNav.makeBottomnavBar(bar, width);

        bottomNav.stats.setOnClickListener(new NavBarOnClickListener(1));
        bottomNav.addWorkout.setOnClickListener(new NavBarOnClickListener(2));
        bottomNav.diary.setOnClickListener(new NavBarOnClickListener(3));

        row.addView(topBar);
        row.addView(labelrow);
        row.addView(rowScroller);
        row.addView(addExerciseButton, buttonParams);
        row.addView(volumeRow);
        row.setLayoutParams(bottomPara);
        // totalLayout.addView(topBar);
        totalLayout.addView(row);
        totalLayout.addView(divider);
        totalLayout.addView(bar);
        //row.addView(bottomBar, bottomPara);
        testLayout.addView(totalLayout, bottomPara);


    }

    class Adder extends AddRowOnClickListener {

    }
    class BottomNav {
        LinearLayout addWorkout = new LinearLayout(getBaseContext());
        LinearLayout diary = new LinearLayout(getBaseContext());
        LinearLayout stats = new LinearLayout(getBaseContext());

        public void makeBottomnavBar(LinearLayout layout, int widths) {
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.setMinimumWidth(widths);
            //layout.setBackgroundColor(Color.BLACK);

            LinearLayout.LayoutParams layoutpara = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutpara.gravity = Gravity.CENTER;
            LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            para.gravity = Gravity.CENTER;


            TextView statText = new TextView(getBaseContext());
            statText.setText("Stats");
            statText.setTextColor(green);
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
            addText.setTextColor(green);
            addText.setLayoutParams(layoutpara);
            addWorkout.addView(addText);
            addWorkout.setMinimumWidth(widths / 3);
            addWorkout.setMinimumHeight(160);
            addWorkout.setBackgroundColor(Color.rgb(46,52,56));
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
            diar.setTextColor(green);

            layout.addView(stats);
            layout.addView(addWorkout);
            layout.addView(diary);
        }

    }


    private void setSupportActionBar(Toolbar toolbar) {
    }


    public void makeLinearLayoutParamsTopBar(LinearLayout layout) {
        LinearLayout.LayoutParams layoutpara = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutpara.setMargins(0, 0, 0, 0);
        layoutpara.gravity = Gravity.CENTER;
        layout.setLayoutParams(layoutpara);
    }

    public void makeLinearLayoutParams(LinearLayout layout) {
        LinearLayout.LayoutParams layoutpara = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutpara.setMargins(10, 10, 10, 10);
        layoutpara.gravity = Gravity.CENTER;
        layout.setLayoutParams(layoutpara);
    }

    public void makeButtonAndTextRowParams(LinearLayout.LayoutParams params) {
        params.leftMargin = 10;
        params.rightMargin = 10;
        params.topMargin = 20;
        params.bottomMargin = 20;
        params.gravity = Gravity.CENTER_HORIZONTAL;
    }

    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    class SaveWorkoutListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            SaveWorkout test = new SaveWorkout();
            test.WriteObjectToFile(thisWorkout, v.getContext());
        }
    }


      public void makeLabels(LinearLayout linear) {
        // linear layoutcreator
        MyLinearLayout creatLinearLayout = new MyLinearLayout();


        //Make Labels:
         TextView nameLabel = new TextView(this);
         nameLabel.setText("Exercise");
         nameLabel.setPadding(60, 0, 0, 0);
         nameLabel.setTextSize(12);
         nameLabel.setTextColor(WHITE);

         TextView repsLabel = new TextView(this);
         repsLabel.setText("Reps");
         repsLabel.setPadding(110, 0, 0, 0);
         repsLabel.setTextSize(12);
         repsLabel.setTextColor(WHITE);

         TextView setsLabel = new TextView(this);
         setsLabel.setText("Sets");
         setsLabel.setPadding(70, 0, 0, 0);
         setsLabel.setTextSize(12);
         setsLabel.setTextColor(WHITE);

         TextView weightLabel = new TextView(this);
         weightLabel.setText("Weight");
         weightLabel.setPadding(60, 0, 0, 0);
         weightLabel.setTextSize(12);
         weightLabel.setTextColor(WHITE);

         TextView restLabel = new TextView(this);
         restLabel.setText("Rest");
         restLabel.setPadding(45, 0, 10, 0);
         restLabel.setTextSize(12);
         restLabel.setTextColor(WHITE);

         creatLinearLayout.setupLayout(linear);
         makeLinearLayoutParams(linear);
         linear.addView(nameLabel);
         linear.addView(repsLabel);
         linear.addView(setsLabel);
         linear.addView(weightLabel);
         linear.addView(restLabel);

      }

}

class Exercise implements Serializable{

    private String name;
    private int reps;
    private int sets;
    private int weight;
    private int rest;
    private int volume;


    public void setName(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public void setReps(String r) {
        reps = Integer.parseInt(r);
    }

    public int getReps() {
        return reps;
    }

    public void setSets(String s) {
        sets = Integer.parseInt(s);
    }

    public int getSets() {
        return sets;
    }

    public void setWeight(String w) {
        weight = Integer.parseInt(w);
    }

    public int getWeight() {
        return weight;
    }

    public void setRest(String rst) {
        rest = Integer.parseInt(rst);
    }

    public int getRest() {
        return rest;
    }

    public void setVolume(int r, int s) {
        volume = r * s;
    }

    public int getVolume() {
        return volume;
    }

}

class MyLinearLayout {
    public void setupLayout(LinearLayout linlayout) {
        //linlayout.setBackgroundColor(Color.rgb(0,0,100));
        linlayout.setOrientation(LinearLayout.HORIZONTAL);
        linlayout.setPadding(10, 10, 10, 10);
    }
}

class MyButton {

    public void makeButton(Button butn) {
        butn.setTextColor(MainActivity.grey);
        butn.setBackgroundColor(MainActivity.green);
        butn.setTextSize(20);

    }
}


class myTextField {
    public void makeTextField(EditText texfield, int widths) {
        texfield.setBackgroundColor(MainActivity.green);
        texfield.setTextColor(MainActivity.grey);
        texfield.setTextSize(15);
        texfield.setTypeface(null, Typeface.BOLD);
        texfield.setMaxHeight(100);
        texfield.setMaxLines(3);
        texfield.setMaxWidth(widths);

    }
}
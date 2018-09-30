package com.example.kalle.workoutdiary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class AddWorkoutClass {

    Button addExerciseButton;
    Button saveButton;
    Button add;
    Button startNewWorkoutButton;

    static LinearLayout exerciseRowContainer;
    ScrollView rowScroller;
    static TextView volumeLabel;
    static ArrayList<LinearLayout> rowList;

    ArrayList<Exercise> exerciseList;
    static ArrayList<ArrayList> containerFortestxArray;
    static ArrayList<EditText> textFieldArray;
    static LinearLayout rowContainer;
    static Workout thisWorkout;
    static int numberOfExercises;
    static int vol;

    static int numWorkouts;

    public ArrayList<Exercise> savedExercises = new ArrayList<Exercise>();
    ArrayList<Workout> savedWorkouts = new ArrayList<Workout>();


    public static LinearLayout createLayout(View v, LinearLayout containers) {
        make(containers, v);
        return containers;
    }

    public static void make(LinearLayout containers, View v) {

        thisWorkout = new Workout();


        LinearLayout containerRows = new LinearLayout(v.getContext());
        containerRows.setMinimumWidth(BottomNaviClass.width);
        containerRows.setMinimumHeight(BottomNaviClass.height - 160);
        LinearLayout labelsRow = new LinearLayout(v.getContext());
        LinearLayout volumeRow = new LinearLayout(v.getContext());
        LinearLayout divider = new LinearLayout(v.getContext());

        GridLayout grid = new GridLayout(v.getContext());

        Button addExerciseButtton = new Button(v.getContext());
        makeAdderButton(addExerciseButtton);

        ScrollView rowScroller = new ScrollView(v.getContext());

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        SetUp.makeButtonAndTextRowParams(buttonParams);

        LinearLayout.LayoutParams bottomPara = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, Gravity.BOTTOM);

        makeTopBar(v, grid);
        makeLabelsRow(v, labelsRow);
        makeTextRows(v, rowScroller);
        makeVolRow(v, volumeRow);

        containerRows.addView(grid);
        containerRows.addView(labelsRow);
        containerRows.addView(rowScroller);
        containerRows.addView(addExerciseButtton, buttonParams);
        containerRows.addView(volumeRow, buttonParams);
        containerRows.setOrientation(LinearLayout.VERTICAL);

        containers.addView(containerRows, bottomPara);
        containers.setBackgroundColor(BottomNaviClass.grey);
    }

    public static void makeVolRow(View v, LinearLayout volRow) {
        volumeLabel = new TextView(v.getContext());
        volumeLabel.setText("Total voume: " + 0 + "kg");
        volRow.addView(volumeLabel);
    }

    public static void makeAdderButton(Button addBtn) {
        MyButton.makeButton(addBtn, "green");
        addBtn.setText("add");
        addBtn.setTextColor(BottomNaviClass.grey);
        addBtn.setOnClickListener(new AddRowOnClickListener());
    }


    public static void makeTextRows(View v, ScrollView scrollView) {
        rowContainer = new LinearLayout(v.getContext());
        numberOfExercises = 0;

        setupRowContainer(v, rowContainer);

        scrollView.setLayoutParams(new RelativeLayout.LayoutParams(
                ScrollView.LayoutParams.FILL_PARENT, BottomNaviClass.height - 1400));
        scrollView.setMinimumHeight(BottomNaviClass.height - 1400);

        rowContainer.setOrientation(LinearLayout.VERTICAL);

        scrollView.addView(rowContainer);
    }

    public static void setupRowContainer(View v, LinearLayout rowContainer) {
        containerFortestxArray = new ArrayList<ArrayList>();
        textFieldArray = new ArrayList<>();
        rowList = new ArrayList<LinearLayout>();
        rowList.add(new LinearLayout(v.getContext()));

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        SetUp.makeButtonAndTextRowParams(buttonParams);

        TextFieldMaker.makeTextFields(rowList.get(numberOfExercises), v, containerFortestxArray, textFieldArray, numberOfExercises);
        rowContainer.addView(rowList.get(numberOfExercises), buttonParams);
        numberOfExercises = numberOfExercises + 1;
    }

    public static void makeLabelsRow(View v, LinearLayout labelrow) {
        SetUp.makeLabels(labelrow, v.getContext(), BottomNaviClass.width);
    }


    public static void makeTopBar(View v, GridLayout grid) {
        //Add layouts to grid, add buttons to layouts
        LinearLayout saveLayout = new LinearLayout(v.getContext());
        LinearLayout middleLayout = new LinearLayout(v.getContext());
        LinearLayout addRowLayout = new LinearLayout(v.getContext());

        makeTopBarLayouts(v, saveLayout, middleLayout, addRowLayout);

        grid.setOrientation(GridLayout.HORIZONTAL);
        grid.addView(saveLayout);
        grid.addView(middleLayout);
        grid.addView(addRowLayout);
        grid.setBackgroundColor(BottomNaviClass.lightBlack);

    }

    public static void makeTopBarLayouts(View v, LinearLayout top1, LinearLayout top2, LinearLayout top3) {
        int thirdWidth = BottomNaviClass.width / 3;
        top1.setMinimumHeight(100);
        top1.setMinimumWidth(thirdWidth);
        top2.setMinimumHeight(100);
        top2.setMinimumWidth(thirdWidth);
        top3.setMinimumHeight(100);
        top3.setMinimumWidth(thirdWidth);

        Button saveButton = new Button(v.getContext());
        ImageButton middleButton = new ImageButton(v.getContext());
        Button addRowButton = new Button(v.getContext());
        createTopButtons(v, saveButton, addRowButton, middleButton);

        top1.addView(saveButton);
        top2.addView(middleButton);
        top3.addView(addRowButton);

    }

    public static void createTopButtons(View v, Button save, Button add, ImageButton middle) {

        add.setOnClickListener(new AddRowOnClickListener());
        add.setText("+");
        add.setTextSize(30);
        add.setTextColor(BottomNaviClass.green);
        add.setBackgroundColor(BottomNaviClass.lightBlack);
        add.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);

        //saveButton.setBackgroundResource(R.mipmap.ticks);
        save.setText("✓");
        save.setTextSize(30);
        save.setTextColor(BottomNaviClass.green);
        save.setBackgroundColor(BottomNaviClass.lightBlack);
        save.setOnClickListener(new SaveWorkoutListener());

        // make save button
        middle.setBackgroundColor(BottomNaviClass.lightBlack);
    }


    static class SaveWorkoutListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            SaveWorkout test = new SaveWorkout();
            test.WriteObjectToFile(thisWorkout, v.getContext());
            SaveWorkout.checkExistingFiles(v.getContext());
            Context context = v.getContext();
            Intent intent = new Intent(context, BottomNaviClass.class);
            BottomNaviClass.identifier = 3;
            context.startActivity(intent);
        }
    }
}

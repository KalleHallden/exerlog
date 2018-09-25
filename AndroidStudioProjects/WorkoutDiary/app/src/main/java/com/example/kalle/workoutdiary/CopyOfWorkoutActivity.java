package com.example.kalle.workoutdiary;

import android.app.AppComponentFactory;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import static android.graphics.Color.WHITE;
import static android.graphics.Color.rgb;

public class CopyOfWorkoutActivity extends AppCompatActivity {

   // Diary diary;
    Workout workout;

    Button addExerciseButton;
    Button saveButton;
    Button one;
    Button startNewWorkoutButton;
    LinearLayout row;
    static LinearLayout exerciseRowContainer;
    ScrollView rowScroller;
    static TextView volumeLabel;
    static ArrayList<LinearLayout> rowList;
    ArrayList<Exercise> exerciseList = new ArrayList<Exercise>();
    static ArrayList<ArrayList> containerFortestxArray;
    static ArrayList<EditText> textFieldArray;
    static int numberOfExercises;
    static int vol;


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        System.out.println("We are in!!!");

        buildit();
        System.out.println("We built this city!!");


    }

    public void setUp3(Workout w) {

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(MainActivity.black);


            Color.green(Color.rgb(4,168,46));

        numberOfExercises = 0;
        exerciseList = new ArrayList<Exercise>();
        containerFortestxArray = new ArrayList<ArrayList>();
        rowList = new ArrayList<LinearLayout>();
        View view = new View(this);

        //buildit();
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        int thirdWidth = (int)(width*0.333333);

        LinearLayout testLayout = new LinearLayout(this);
        testLayout.setBackgroundColor(MainActivity.grey);
        setContentView(testLayout);

        LinearLayout totalLayout = new LinearLayout(this);
        totalLayout.setOrientation(LinearLayout.VERTICAL);


        //Make rows for labels, exercise rows and button
        row = new LinearLayout(this);
        row.setOrientation(LinearLayout.VERTICAL);

        // makeLinearLayoutParams(row);


        LinearLayout topBar = new LinearLayout(this);
        topBar.setBackgroundColor(Color.rgb(30,30,30));

        LinearLayout layoutTop = new LinearLayout(this);
        //layoutTop.setBackgroundColor(Color.BLUE);

        LinearLayout layoutTop2 = new LinearLayout(this);
        //layoutTop2.setBackgroundColor(Color.GREEN);
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
        one.setOnClickListener(new DeleteWorkoutListener());
        one.setText("Delete");
        one.setTextSize(30);
        one.setTextColor(MainActivity.green);
        one.setBackgroundColor(Color.rgb(30,30,30));
        one.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);








        ImageView tick = new ImageView(this);
        tick.setImageResource(R.mipmap.ticks);

        layoutTop.addView(saveButton);
        layoutTop2.addView(startNewWorkoutButton);
        layoutTop3.addView(one);



        //saveButton.setBackgroundResource(R.mipmap.ticks);
        saveButton.setText("✓");
        saveButton.setTextSize(30);
        saveButton.setTextColor(MainActivity.green);
        saveButton.setBackgroundColor(topBar.getSolidColor());
        saveButton.setOnClickListener(new SaveWorkoutListener());

        startNewWorkoutButton.setBackgroundColor(topBar.getSolidColor());


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
        SetUp.makeLabels(labelrow, view.getContext(), width);

        volumeLabel = new TextView(this);
        volumeLabel.setText("Total Volume: 0");
        volumeLabel.setTextColor(WHITE);
        LinearLayout volumeRow = new LinearLayout(this);



        // Make container for Exercise rows
        exerciseRowContainer = new LinearLayout(this);
        exerciseRowContainer.setOrientation(LinearLayout.VERTICAL);
        rowScroller = new ScrollView(this);
        rowScroller.setLayoutParams(new RelativeLayout.LayoutParams(
                ScrollView.LayoutParams.FILL_PARENT, 900));

        // make save button
        MyButton makbtn = new MyButton();
        addExerciseButton = new Button(this);
        makbtn.makeButton(addExerciseButton, "green");
        addExerciseButton.setText("Add");

        addExerciseButton.setOnClickListener(new AddRowOnClickListenerCopy());


        // Button Parameter creator
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        SetUp.makeButtonAndTextRowParams(buttonParams);

        int listlength = Workout.savedExercisez.size();
        System.out.println("Size of list = " + listlength);

        ArrayList<LinearLayout> textFieldRows = new ArrayList<LinearLayout>();
        //listlength = listlength/2;
        vol = 0;

        if (listlength != 0) {
            for (int s = 0; s < listlength; s++) {
                System.out.println("We are in the matrix: " + s);
                rowList.add(new LinearLayout(this));
                textFieldRows.add(new LinearLayout(getBaseContext()));
                //LinearLayout textFieldRows = rowList.get(numberOfExercises);
                LinearLayout rowsss = textFieldRows.get(s);
                TextFieldMakerCopy.makeTextFields(rowsss, view);
                exerciseRowContainer.addView(rowsss, buttonParams);
                numberOfExercises = numberOfExercises + 1;

                Exercise ex = w.savedExercisez.get(s);
                exerciseList.add(new Exercise());
                Exercise exer = exerciseList.get(s);

                for (int x = 0; x < 5; x++) {
                    EditText text = textFieldArray.get(x);
                    if (x == 0) {
                        text.setText(ex.getName());
                        try {
                            exer.setName(Integer.toString(ex.getReps()));
                        } catch (Exception f) {
                            exer.setName(" ");
                        }
                    } if (x == 1) {
                        int r = ex.getReps();
                        text.setText(Integer.toString(r));
                        try {
                            exer.setReps(Integer.toString(ex.getReps()));
                        } catch (Exception f) {
                            exer.setReps("0");
                        }
                    } if (x == 2) {
                        int r = ex.getSets();
                        text.setText(Integer.toString(r));
                        try {
                            exer.setSets(Integer.toString(ex.getSets()));
                        } catch (Exception f) {
                            exer.setSets("1");
                        }
                    } if (x == 3) {
                        int r = ex.getWeight();
                        text.setText(Integer.toString(r));
                        try {
                            exer.setWeight(Integer.toString(ex.getWeight()));
                        } catch (Exception f) {
                            exer.setWeight("0");
                        }
                    } if (x == 4) {
                        int r = ex.getRest();
                        text.setText(Integer.toString(r));
                        try {
                            exer.setRest(Integer.toString(ex.getRest()));
                        } catch (Exception f) {
                            exer.setRest("0");
                        }
                    }
                }
                ex.setVolume(ex.getReps(), ex.getSets());
                vol = vol + ex.getVolume();
                volumeLabel.setText("Total Volume: " + vol);
            }
        } else {
                System.out.println("We are NOT in the matrix!!");
                rowList.add(new LinearLayout(this));
                LinearLayout textFieldRowz = rowList.get(numberOfExercises);
                TextFieldMakerCopy.makeTextFields(textFieldRowz, view);
                exerciseRowContainer.addView(textFieldRowz, buttonParams);
                numberOfExercises = numberOfExercises + 1;
        }
        System.out.println("We left the matrix");

        workout = w;

        rowScroller.addView(exerciseRowContainer);

        SetUp.makeLinearLayoutParams(volumeRow);
        volumeRow.addView(volumeLabel);
        SetUp.makeLinearLayoutParamsTopBar(totalLayout);

        row.addView(topBar);
        row.addView(labelrow);
        row.addView(rowScroller);
        row.addView(addExerciseButton, buttonParams);
        row.addView(volumeRow);
        // totalLayout.addView(topBar);
        totalLayout.addView(row);
        testLayout.addView(totalLayout);


    }

    class DeleteWorkoutListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String filename = "Workout" + DiaryActivity.identify;
            deleteFile(filename);
            SaveWorkout.n -= 1;
            Workout.savedExercisez.clear();
            DiaryActivity.removeRow(DiaryActivity.identify);

            Context context = v.getContext();
            Intent intent = new Intent(context, DiaryActivity.class);
            context.startActivity(intent);
            System.out.println("File should get deleted");

        }
    }

    class GoBackOnClickListener implements  View.OnClickListener {
            @Override
             public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, DiaryActivity.class);
                context.startActivity(intent);
            }
    }

    class SaveWorkoutListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            SaveWorkout savespec = new SaveWorkout();
            savespec.saveSpecific(DiaryActivity.identify, v.getContext(), workout);
            saveButton.setText("Back");
            saveButton.setOnClickListener(new GoBackOnClickListener());
        }
    }

    public void buildit() {
        View view = new View(this);
        System.out.println("We are here ");
        System.out.println("This is identity: " + DiaryActivity.identify);
        //Diary.numWorkouts = Diary.numWorkouts - 1;

        //Workout workouts = Diary.openSpecificWorkout(view.getContext(), (DiaryActivity.identify), workout);
        SaveWorkout test = new SaveWorkout();
        Workout workout = test.openWorkout(DiaryActivity.identify, view.getContext());
        setUp3(workout);

        }

}




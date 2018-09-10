package com.example.kalle.workoutdiary;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.ImageDecoder;
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

public class MainActivity extends AppCompatActivity {

    Button addExerciseButton;
    Button saveButton;
    Button one;
    Button startNewWorkoutButton;
    LinearLayout row;
    LinearLayout exerciseRowContainer;
    ScrollView rowScroller;
    TextView volumeLabel;
    ArrayList<LinearLayout> rowList = new ArrayList<LinearLayout>();
    ArrayList<Exercise> exerciseList = new ArrayList<Exercise>();
    ArrayList<ArrayList> containerFortestxArray = new ArrayList<ArrayList>();
    ArrayList<EditText> textFieldArray;
    Workout thisWorkout;
    int numberOfExercises;
    int vol;




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
        int thirdWidth = (int)(width*0.333333);

        LinearLayout testLayout = new LinearLayout(this);
        testLayout.setBackgroundColor(rgb(43, 43, 43));
        setContentView(testLayout);

        LinearLayout totalLayout = new LinearLayout(this);
        totalLayout.setOrientation(LinearLayout.VERTICAL);


        //Make rows for labels, exercise rows and button
        row = new LinearLayout(this);
        row.setOrientation(LinearLayout.VERTICAL);

       // makeLinearLayoutParams(row);


        LinearLayout topBar = new LinearLayout(this);
        topBar.setBackgroundColor(Color.rgb(10,10,10));

        LinearLayout layoutTop = new LinearLayout(this);
        //layoutTop.setBackgroundColor(Color.BLUE);

        LinearLayout layoutTop2 = new LinearLayout(this);
        layoutTop2.setBackgroundColor(Color.GREEN);
        LinearLayout layoutTop3 = new LinearLayout(this);
        layoutTop3.setBackgroundColor(Color.WHITE);


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
        one.setOnClickListener(new ShowOldWorkoutListener());

        ImageView tick = new ImageView(this);
        tick.setImageResource(R.mipmap.ticks);

        layoutTop.addView(saveButton);
        layoutTop2.addView(startNewWorkoutButton);
        layoutTop3.addView(one);

        startNewWorkoutButton.setOnClickListener(new StartNewWorkoutListener());

        //saveButton.setBackgroundResource(R.mipmap.ticks);
        saveButton.setText("✓");
        saveButton.setTextSize(25);
        saveButton.setTextColor(Color.RED);
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
                ScrollView.LayoutParams.FILL_PARENT, 900));

        // make save button
        MyButton makbtn = new MyButton();
        addExerciseButton = new Button(this);
        makbtn.makeButton(addExerciseButton);
        addExerciseButton.setText("Add");
        addExerciseButton.setOnClickListener(new AddRowOnClickListener());

        // Button Parameter creator
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        makeButtonAndTextRowParams(buttonParams);

        // Make initial row of textfields and increment number of rows by 1 and add it to the exerciseRowContainer
        rowList.add(new LinearLayout(this));
        LinearLayout textFieldRows = rowList.get(numberOfExercises);
        makeTextFields(textFieldRows);
        exerciseRowContainer.addView(textFieldRows, buttonParams);
        numberOfExercises = numberOfExercises + 1;
        rowScroller.addView(exerciseRowContainer);

        makeLinearLayoutParams(volumeRow);
        volumeRow.addView(volumeLabel);
        makeLinearLayoutParamsTopBar(totalLayout);

        row.addView(topBar);
        row.addView(labelrow);
        row.addView(rowScroller);
        row.addView(addExerciseButton, buttonParams);
        row.addView(volumeRow);
       // totalLayout.addView(topBar);
        totalLayout.addView(row);
        testLayout.addView(totalLayout);


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

    int y;
    class ShowOldWorkoutListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, CopyOfWorkoutActivity.class);
            context.startActivity(intent);
            setUp();

            System.out.println("we got called: " + y + " times");
            y++;
        }
    }

        class StartNewWorkoutListener implements View.OnClickListener {

            @Override
            public void onClick(View view) {

                int number = 0;
                int c = 0;



                try {
                    String filename = getFileNameExercises();
                    FileInputStream fis = null;
                    try {
                        fis = openFileInput(filename);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader bufferedReader = new BufferedReader(isr);
                    StringBuffer sb = new StringBuffer();
                    String line;


                        while ((line = bufferedReader.readLine()) != null) {
                            c++;
                            System.out.println("This is how many times it runs " + c);
                            sb.append(line);
                            String text = (String) line;
                            System.out.println("This is the total line: " + text);

                            String[] data = text.split("END");

                            for (int d = 0; d < data.length; d++) {
                                System.out.println("This is current: " + data[d]);
                                String dat = data[d];
                                String[] info = dat.split("SPLIT");

                                savedExercises.add(new Exercise());
                                Exercise exer = savedExercises.get(d);
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
                            isr.close();

                        }

                    } catch (IOException e) {
                        System.out.println("Exception");
                    }
                   // System.out.println("This is current: " + sb);

            }
        }

        class SaveWorkoutListener implements  View.OnClickListener {
           @Override
            public void onClick(View v) {


               savedWorkouts.add(new Workout());

               //This is where you save the workout
               String filename = getFileNameExercises();
               System.out.println("This is the name of the file you are creating: " + filename);
               File f = new File(getFileNameExercises());
               //FileOutputStream outputStream;

               try {
                   FileOutputStream outputStream;
                   outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                   for (int i = 0; i < numberOfExercises; i++) {
                       Exercise exer = exerciseList.get(i);
                       String name = exer.getName();
                       String reps = Integer.toString(exer.getReps());
                       String sets = Integer.toString(exer.getSets());
                       String weight = Integer.toString(exer.getWeight());
                       String rest = Integer.toString(exer.getRest());
                       outputStream.write(name.getBytes());
                       String split = "SPLIT";
                       outputStream.write(split.getBytes());
                       outputStream.write(reps.getBytes());
                       outputStream.write(split.getBytes());
                       outputStream.write(sets.getBytes());
                       outputStream.write(split.getBytes());
                       outputStream.write(weight.getBytes());
                       outputStream.write(split.getBytes());
                       outputStream.write(rest.getBytes());
                       outputStream.write(split.getBytes());
                       String stopper = "END";
                       outputStream.write(stopper.getBytes());
                       //outputStream.write(Integer.parseInt("END"));
                   }
                   String volume = Integer.toString(vol);
                   //outputStream.write(Integer.parseInt("\n"));
                   outputStream.write(volume.getBytes());
                   outputStream.close();
               } catch (Exception e) {
                   e.printStackTrace();
               }

               String workoutName = getFileNameWorkout();
               FileOutputStream os;

               try {
                   os = openFileOutput(workoutName, Context.MODE_PRIVATE);
                   for (int i = 0; i < savedWorkouts.size(); i++) {
                       Workout wo = savedWorkouts.get(i);
                       String volume = Integer.toString(wo.getWorkoutVolume());
                       os.write(volume.getBytes());
                       String stopper = "END";
                       os.write(stopper.getBytes());
                   }
                   os.close();
               } catch (Exception e) {
                   e.printStackTrace();
               }

           }
        }

         class AddRowOnClickListener implements View.OnClickListener {

        @Override


             public void onClick(View v) {

                LinearLayout.LayoutParams textRowParams = new LinearLayout.LayoutParams(
                          LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                makeButtonAndTextRowParams(textRowParams);
                vol = 0;

                rowList.add(new LinearLayout(getBaseContext()));
                LinearLayout textFieldRows = rowList.get(numberOfExercises);

                makeTextFields(textFieldRows);
                exerciseRowContainer.addView(textFieldRows, textRowParams);



                for (int i = 0; i < numberOfExercises; i++ ) {
                    int x = numberOfExercises * 5;

                    exerciseList.add(new Exercise());
                    Exercise exer = exerciseList.get(i);
                    ArrayList<EditText> rowinfo = containerFortestxArray.get(i);

                    for (int j = 0; j < 5; j++) {
                        EditText textField = rowinfo.get(j);
                        String ys = textField.getText().toString();
                        if (j == 0) {
                            try {
                                exer.setName(ys);
                            } catch (Exception a) {
                                exer.setName(" ");
                            }
                        }
                        if (j == 1) {
                            try {
                                exer.setReps(ys);
                            } catch (Exception a) {
                                exer.setReps("0");
                            }
                        }
                        if (j == 2) {
                            try {
                                exer.setSets(ys);
                            } catch (Exception a) {
                                exer.setSets("1");
                            }
                        }
                        if (j == 3) {
                            try {
                                exer.setWeight(ys);
                            } catch (Exception a) {
                                exer.setWeight("0");
                            }
                        }
                        if (j == 4) {
                            try {
                                exer.setRest(ys);
                            } catch (Exception a) {
                                exer.setRest("0");
                            }
                        }
                    }
                    exer.setVolume(exer.getReps(), exer.getSets());
                    vol = vol + exer.getVolume();
                }

            numberOfExercises++;
                String volString = Integer.toString(vol);
                volumeLabel.setText("Total Volume: " + volString);
             }
         }


      public void makeLabels(LinearLayout linear) {
        // linear layoutcreator
        MyLinearLayout creatLinearLayout = new MyLinearLayout();


        //Make Labels:
         TextView nameLabel = new TextView(this);
         nameLabel.setText("Exercise");
         nameLabel.setPadding(60, 0, 0, 0);
         nameLabel.setTextSize(15);
         nameLabel.setTextColor(WHITE);

         TextView repsLabel = new TextView(this);
         repsLabel.setText("Reps");
         repsLabel.setPadding(110, 0, 0, 0);
         repsLabel.setTextSize(15);
         repsLabel.setTextColor(WHITE);

         TextView setsLabel = new TextView(this);
         setsLabel.setText("Sets");
         setsLabel.setPadding(70, 0, 0, 0);
         setsLabel.setTextSize(15);
         setsLabel.setTextColor(WHITE);

         TextView weightLabel = new TextView(this);
         weightLabel.setText("Weight");
         weightLabel.setPadding(60, 0, 0, 0);
         weightLabel.setTextSize(15);
         weightLabel.setTextColor(WHITE);

         TextView restLabel = new TextView(this);
         restLabel.setText("Rest");
         restLabel.setPadding(45, 0, 10, 0);
         restLabel.setTextSize(15);
         restLabel.setTextColor(WHITE);

         creatLinearLayout.setupLayout(linear);
         makeLinearLayoutParams(linear);
         linear.addView(nameLabel);
         linear.addView(repsLabel);
         linear.addView(setsLabel);
         linear.addView(weightLabel);
         linear.addView(restLabel);

      }








      public void makeTextFields(LinearLayout linlay) {

        containerFortestxArray.add(new ArrayList<EditText>());
        textFieldArray = containerFortestxArray.get(numberOfExercises);

                  LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                     LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

             layoutParams.setMargins(10, 0, 10, 0);
             layoutParams.gravity = Gravity.CENTER;


          LinearLayout.LayoutParams leftMarginParams = new LinearLayout.LayoutParams(
                  LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
          leftMarginParams.leftMargin = 10;
          leftMarginParams.rightMargin = 5;

          // make textfield
          EditText exerciseName = new EditText(this);
          myTextField maktxt = new myTextField();
          maktxt.makeTextField(exerciseName);
          //exerciseName.setMaxWidth(300);
          exerciseName.setMinimumWidth(300);
          exerciseName.setPadding(10,5,10,5);
          textFieldArray.add(exerciseName);

          // second textfield
          EditText repsTextField = new EditText(this);
              maktxt.makeTextField(repsTextField);
             // repsTextField.setMaxWidth(150);
              repsTextField.setMinimumWidth(150);
              repsTextField.setPadding(10,5,10,5);
              textFieldArray.add(repsTextField);

          // third textField
          EditText setsTextField = new EditText(this);
              maktxt.makeTextField(setsTextField);
             // setsTextField.setMaxWidth(150);
              setsTextField.setMinimumWidth(150);
              setsTextField.setPadding(10,5,10,5);
              textFieldArray.add(setsTextField);

          //fourth textField
          EditText weightTextField = new EditText(this);
              maktxt.makeTextField(weightTextField);
             // weightTextField.setMaxWidth(150);
              weightTextField.setMinimumWidth(150);
              weightTextField.setPadding(10,5,10,5);
              textFieldArray.add(weightTextField);

          // fifth textField
          EditText restTextField = new EditText(this);
              maktxt.makeTextField(restTextField);
             // restTextField.setMaxWidth(150);
              restTextField.setMinimumWidth(150);
              restTextField.setPadding(10,5,10,5);
              textFieldArray.add(restTextField);

              linlay.setLayoutParams(layoutParams);

              //textFieldRows.setLayoutParams(layoutParams);
              linlay.addView(exerciseName,leftMarginParams);
              linlay.addView(repsTextField,leftMarginParams);
              linlay.addView(setsTextField,leftMarginParams);
              linlay.addView(weightTextField,leftMarginParams);
              linlay.addView(restTextField,leftMarginParams);


      }

}

class Workout {
    private int workoutVolume;
    private String workoutName;
    private ArrayList<Exercise> workoutExerciseList;

    public void setWorkoutName(String name) {
        workoutName = name;
    }

    public String getWorkoutName() {
       return workoutName;
    }

    public void setVolume(int v) {
        workoutVolume = v;
    }

    public int getWorkoutVolume() {
        return  workoutVolume;
    }
}

class Exercise {

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
        butn.setTextColor(rgb(150, 0, 0));
        butn.setBackgroundColor(rgb(43, 43, 43));
        butn.setTextSize(20);

    }
}


class myTextField {
    public void makeTextField(EditText texfield) {
        texfield.setBackgroundColor(rgb(0,0,0));
        texfield.setTextColor(rgb(150, 0, 0));
        texfield.setTextSize(12);
        texfield.setMaxHeight(100);

    }
}
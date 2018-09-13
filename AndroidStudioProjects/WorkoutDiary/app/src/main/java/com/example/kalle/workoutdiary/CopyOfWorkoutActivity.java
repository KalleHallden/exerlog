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

public class CopyOfWorkoutActivity extends MainActivity {

    private static int workoutNumber;
    public void setWorkout(int num) {
        workoutNumber = num;
    }
    public int getWorkout() {
        return workoutNumber;
    }

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
    int numberOfExercises;
    int vol;

    MainActivity main;

    ArrayList<Exercise> savedExercisez = new ArrayList<Exercise>();


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        System.out.println("We are in!!!");
            buildit();
            System.out.println("We built this city!!");
        setUp();

    }

    public void setUp() {



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

        ImageView tick = new ImageView(this);
        tick.setImageResource(R.mipmap.ticks);

        layoutTop.addView(saveButton);
        layoutTop2.addView(startNewWorkoutButton);
        layoutTop3.addView(one);



        //saveButton.setBackgroundResource(R.mipmap.ticks);
        saveButton.setText("✓");
        saveButton.setTextSize(25);
        saveButton.setTextColor(Color.RED);
        saveButton.setBackgroundColor(topBar.getSolidColor());
        saveButton.setOnClickListener(new SaveWorkoutListener());


        startNewWorkoutButton.setOnClickListener(new ShowOldWorkoutListener());



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

        int listlength = savedExercisez.size();
        int length2 = exerciseList.size();

        ArrayList<LinearLayout> textFieldRows = new ArrayList<LinearLayout>();

        if (listlength != 0) {
            for (int s = 0; s < listlength; s++) {
                System.out.println("We are in the matrix: " + s);
                rowList.add(new LinearLayout(this));
                textFieldRows.add(new LinearLayout(getBaseContext()));
                //LinearLayout textFieldRows = rowList.get(numberOfExercises);
                LinearLayout rowsss = textFieldRows.get(s);
                makeTextFields(rowsss);
                exerciseRowContainer.addView(rowsss, buttonParams);
                numberOfExercises = numberOfExercises + 1;

                Exercise ex = savedExercisez.get(s);
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
                makeTextFields(textFieldRowz);
                exerciseRowContainer.addView(textFieldRowz, buttonParams);
                numberOfExercises = numberOfExercises + 1;
        }
        System.out.println("We left the matrix");


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

    class DeleteWorkoutListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String filename = "Exercises" + workoutNumber;
            deleteFile(filename);

            Context context = v.getContext();
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
            System.out.println("File should get deleted");

        }
    }

    class SaveWorkoutListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            int count = 0;
            savedWorkouts.add(new Workout());
            Boolean existing = true;

            //This is where you save the workout
            String filename = "Exercises" + workoutNumber;

            File fouts = new File(filename);

            deleteFile(filename);
            FileInputStream fis = null;

                try {
                    fis = openFileInput(filename);
                    System.out.println("Right now you are at Exercise" + workoutNumber);
                } catch (Exception e) {
                    System.out.println("No file");
                }



            try {
                FileOutputStream os = openFileOutput(filename, Context.MODE_PRIVATE);
                System.out.println("Workout number " + filename);

                System.out.println("Creating bufferedwriter");
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(os));
                try {
                    System.out.println("Opening fileoutput");

                    for (int i = 0; i < exerciseList.size(); i++) {
                        System.out.println("Writing files");
                        Exercise exer = exerciseList.get(i);
                        String name = exer.getName();
                        String reps = Integer.toString(exer.getReps());
                        String sets = Integer.toString(exer.getSets());
                        String weight = Integer.toString(exer.getWeight());
                        String rest = Integer.toString(exer.getRest());
                        String split = "SPLIT";
                        String stopper = "END";

                        out.write(name);
                        out.write(split);
                        out.write(reps);
                        out.write(split);
                        out.write(sets);
                        out.write(split);
                        out.write(weight);
                        out.write(split);
                        out.write(rest);
                        out.write(split);
                        out.write(stopper);

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
                out.close();
                os.close();
                out.flush();
                os.flush();
                System.out.println("Done");
            } catch (Exception fileForOutputstreamnotfound) {
                System.out.println("fileForOutputstreamnotfound");
            }
        }
    }


    int y;
    class ShowOldWorkoutListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
            System.out.println("So far so good");

            System.out.println("we got called: " + y + " times");
            y++;
        }
    }


    int c;
    public void buildit() {


            int x = numWorkouts;
            System.out.println("This is numWorkouts: " + workoutNumber);

            String file1 = "Exercises" + workoutNumber;

            System.out.println("You are currently creating this workout: " + file1);



            System.out.println("This is something you are now printing: " + getFilesDir().listFiles().length);

        File f = new File(file1);
        if(f.exists() == false) {
            System.out.println("We are here now");

            System.out.println("We are here now2");
            FileInputStream fis = null;
            try {
                fis = openFileInput(file1);
            } catch (FileNotFoundException e) {
                System.out.println("this is the bloody exception");
                savedExercisez.add(new Exercise());
                Exercise exer = savedExercisez.get(0);
                exer.setName("");
                exer.setReps("0");
                exer.setSets("0");
                exer.setWeight("0");
                exer.setRest("0");
                e.printStackTrace();
            }
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);




            try {
                StringBuffer sb = new StringBuffer();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    c++;
                    System.out.println("This is how many times it runs " + c);
                    sb.append(line);
                    String text = (String) line;
                    System.out.println("This is the total lines int this new one: " + text);

                    String[] data = text.split("END");

                    for (int d = 0; d < data.length; d++) {
                        System.out.println("This is current: " + data[d]);
                        String dat = data[d];
                        String[] info = dat.split("SPLIT");

                        savedExercisez.add(new Exercise());
                        Exercise exer = savedExercisez.get(d);
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
            System.out.println("");






        } else {

            if (f.exists() == true){
                System.out.println("We are here now");
            }

        System.out.println("We are here now3");
        FileInputStream fis = null;
        try {
            fis = openFileInput(file1);
        } catch (FileNotFoundException e) {
            System.out.println("this is the bloody exception");
            savedExercisez.add(new Exercise());
            Exercise exer = savedExercisez.get(0);
            exer.setName("");
            exer.setReps("0");
            exer.setSets("0");
            exer.setWeight("0");
            exer.setRest("0");
            e.printStackTrace();
        }


        System.out.println("");




    }



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
        exerciseName.setPadding(10, 5, 10, 5);
        textFieldArray.add(exerciseName);

        // second textfield
        EditText repsTextField = new EditText(this);
        maktxt.makeTextField(repsTextField);
        // repsTextField.setMaxWidth(150);
        repsTextField.setMinimumWidth(150);
        repsTextField.setPadding(10, 5, 10, 5);
        textFieldArray.add(repsTextField);

        // third textField
        EditText setsTextField = new EditText(this);
        maktxt.makeTextField(setsTextField);
        // setsTextField.setMaxWidth(150);
        setsTextField.setMinimumWidth(150);
        setsTextField.setPadding(10, 5, 10, 5);
        textFieldArray.add(setsTextField);

        //fourth textField
        EditText weightTextField = new EditText(this);
        maktxt.makeTextField(weightTextField);
        // weightTextField.setMaxWidth(150);
        weightTextField.setMinimumWidth(150);
        weightTextField.setPadding(10, 5, 10, 5);
        textFieldArray.add(weightTextField);

        // fifth textField
        EditText restTextField = new EditText(this);
        maktxt.makeTextField(restTextField);
        // restTextField.setMaxWidth(150);
        restTextField.setMinimumWidth(150);
        restTextField.setPadding(10, 5, 10, 5);
        textFieldArray.add(restTextField);

        linlay.setLayoutParams(layoutParams);

        //textFieldRows.setLayoutParams(layoutParams);
        linlay.addView(exerciseName, leftMarginParams);
        linlay.addView(repsTextField, leftMarginParams);
        linlay.addView(setsTextField, leftMarginParams);
        linlay.addView(weightTextField, leftMarginParams);
        linlay.addView(restTextField, leftMarginParams);

    }

}




package com.example.kalle.workoutdiary;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.solver.widgets.ConstraintHorizontalLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Button;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import java.util.ArrayList;

import java.io.*;

import static android.view.ViewGroup.LayoutParams.FILL_PARENT;

public class MainActivity extends AppCompatActivity {

    Button addExerciseButton;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
    }

    public void setUp() {
        RelativeLayout testLayout = new RelativeLayout(this);
        testLayout.setBackgroundColor(Color.rgb(43, 43, 43));
        setContentView(testLayout);

        //Make rows for labels, exercise rows and button
        row = new LinearLayout(this);
        row.setOrientation(LinearLayout.VERTICAL);
        makeLinearLayoutParams(row);

        // Make layout for labels
        LinearLayout labelrow = new LinearLayout(this);
        makeLabels(labelrow);

        volumeLabel = new TextView(this);
        volumeLabel.setText("Total Volume: 0");
        volumeLabel.setTextColor(Color.WHITE);
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

        row.addView(labelrow);
        row.addView(rowScroller);
        row.addView(addExerciseButton, buttonParams);
        row.addView(volumeRow);
        testLayout.addView(row);

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
         nameLabel.setPadding(70, 0, 0, 0);
         nameLabel.setTextSize(12);
         nameLabel.setTextColor(Color.WHITE);

         TextView repsLabel = new TextView(this);
         repsLabel.setText("Reps");
         repsLabel.setPadding(110, 0, 0, 0);
         repsLabel.setTextSize(12);
         repsLabel.setTextColor(Color.WHITE);

         TextView setsLabel = new TextView(this);
         setsLabel.setText("Sets");
         setsLabel.setPadding(70, 0, 0, 0);
         setsLabel.setTextSize(12);
         setsLabel.setTextColor(Color.WHITE);

         TextView weightLabel = new TextView(this);
         weightLabel.setText("Weight");
         weightLabel.setPadding(60, 0, 0, 0);
         weightLabel.setTextSize(12);
         weightLabel.setTextColor(Color.WHITE);

         TextView restLabel = new TextView(this);
         restLabel.setText("Rest");
         restLabel.setPadding(45, 0, 10, 0);
         restLabel.setTextSize(12);
         restLabel.setTextColor(Color.WHITE);

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

             layoutParams.setMargins(10, 10, 10, 10);
             layoutParams.gravity = Gravity.CENTER;


          LinearLayout.LayoutParams leftMarginParams = new LinearLayout.LayoutParams(
                  LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
          leftMarginParams.leftMargin = 10;
          leftMarginParams.rightMargin = 10;

          // make textfield
          EditText exerciseName = new EditText(this);
          myTextField maktxt = new myTextField();
          maktxt.makeTextField(exerciseName);
          exerciseName.setMaxWidth(300);
          exerciseName.setMinimumWidth(300);
          exerciseName.setPadding(10,10,10,10);
          textFieldArray.add(exerciseName);

          // second textfield
          EditText repsTextField = new EditText(this);
              maktxt.makeTextField(repsTextField);
              repsTextField.setMaxWidth(150);
              repsTextField.setMinimumWidth(150);
              repsTextField.setPadding(10,10,10,10);
              textFieldArray.add(repsTextField);

          // third textField
          EditText setsTextField = new EditText(this);
              maktxt.makeTextField(setsTextField);
              setsTextField.setMaxWidth(150);
              setsTextField.setMinimumWidth(150);
              setsTextField.setPadding(10,10,10,10);
              textFieldArray.add(setsTextField);

          //fourth textField
          EditText weightTextField = new EditText(this);
              maktxt.makeTextField(weightTextField);
              weightTextField.setMaxWidth(150);
              weightTextField.setMinimumWidth(150);
              weightTextField.setPadding(10,10,10,10);
              textFieldArray.add(weightTextField);

          // fifth textField
          EditText restTextField = new EditText(this);
              maktxt.makeTextField(restTextField);
              restTextField.setMaxWidth(150);
              restTextField.setMinimumWidth(150);
              restTextField.setPadding(10,10,10,10);
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
        butn.setTextColor(Color.rgb(150, 0, 0));
        butn.setBackgroundColor(Color.rgb(43, 43, 43));
        butn.setTextSize(20);

    }
}


class myTextField {
    public void makeTextField(EditText texfield) {
        texfield.setBackgroundColor(Color.rgb(0,0,0));
        texfield.setTextColor(Color.rgb(150, 0, 0));
        texfield.setTextSize(12);
        texfield.setMaxHeight(100);

    }
}
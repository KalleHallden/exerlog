package com.example.kalle.workoutdiary;

import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class TextFieldMakerCopy {
    static void makeTextFields(LinearLayout linlay, View v) {

        CopyOfWorkoutActivity.containerFortestxArray.add(new ArrayList<EditText>());
        CopyOfWorkoutActivity.textFieldArray = CopyOfWorkoutActivity.containerFortestxArray.get(CopyOfWorkoutActivity.numberOfExercises);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(10, 0, 10, 0);
        layoutParams.gravity = Gravity.CENTER;


        LinearLayout.LayoutParams leftMarginParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        leftMarginParams.leftMargin = 10;
        leftMarginParams.rightMargin = 5;

        // make textfield
        EditText exerciseName = new EditText(v.getContext());
        myTextField maktxt = new myTextField();
        maktxt.makeTextField(exerciseName, 300);
        //exerciseName.setMaxWidth(300);
        exerciseName.setMinimumWidth(300);
        exerciseName.setPadding(10,5,10,5);
        CopyOfWorkoutActivity.textFieldArray.add(exerciseName);

        // second textfield
        EditText repsTextField = new EditText(v.getContext());
        maktxt.makeTextField(repsTextField, 150);
        // repsTextField.setMaxWidth(150);
        repsTextField.setMinimumWidth(150);
        repsTextField.setPadding(10,5,10,5);
        CopyOfWorkoutActivity.textFieldArray.add(repsTextField);

        // third textField
        EditText setsTextField = new EditText(v.getContext());
        maktxt.makeTextField(setsTextField, 150);
        // setsTextField.setMaxWidth(150);
        setsTextField.setMinimumWidth(150);
        setsTextField.setPadding(10,5,10,5);
        CopyOfWorkoutActivity.textFieldArray.add(setsTextField);

        //fourth textField
        EditText weightTextField = new EditText(v.getContext());
        maktxt.makeTextField(weightTextField, 150);
        // weightTextField.setMaxWidth(150);
        weightTextField.setMinimumWidth(150);
        weightTextField.setPadding(10,5,10,5);
        CopyOfWorkoutActivity.textFieldArray.add(weightTextField);

        // fifth textField
        EditText restTextField = new EditText(v.getContext());
        maktxt.makeTextField(restTextField,150);
        // restTextField.setMaxWidth(150);
        restTextField.setMinimumWidth(150);
        restTextField.setPadding(10,5,10,5);
        CopyOfWorkoutActivity.textFieldArray.add(restTextField);

        linlay.setLayoutParams(layoutParams);

        //textFieldRows.setLayoutParams(layoutParams);
        linlay.addView(exerciseName,leftMarginParams);
        linlay.addView(repsTextField,leftMarginParams);
        linlay.addView(setsTextField,leftMarginParams);
        linlay.addView(weightTextField,leftMarginParams);
        linlay.addView(restTextField,leftMarginParams);


    }
}

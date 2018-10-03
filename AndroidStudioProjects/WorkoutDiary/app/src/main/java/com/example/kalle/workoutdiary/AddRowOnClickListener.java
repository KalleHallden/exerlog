package com.example.kalle.workoutdiary;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

class AddRowOnClickListener implements View.OnClickListener {

    //hey

    @Override
    public void onClick(View v) {

        Double weightVol = 0.0;
        System.out.println("we here");
        LinearLayout.LayoutParams textRowParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        makeButtonAndTextRowParams(textRowParams);

        int vol = 0;
        AddWorkoutClass.rowList.add(new LinearLayout(v.getContext()));
        LinearLayout textFieldRows = AddWorkoutClass.rowList.get(AddWorkoutClass.numberOfExercises);

        TextFieldMaker.makeTextFields(textFieldRows, v, AddWorkoutClass.containerFortestxArray, AddWorkoutClass.textFieldArray, AddWorkoutClass.numberOfExercises);
        AddWorkoutClass.rowContainer.addView(textFieldRows, textRowParams);

        Workout.savedExercisez.clear();
        System.out.println("we here");

        for (int i = 0; i < AddWorkoutClass.numberOfExercises; i++ ) {
            System.out.println("we here");

            Workout.savedExercisez.add(new Exercise());
            Exercise ex = AddWorkoutClass.thisWorkout.savedExercisez.get(i);
            System.out.println(i);

            ArrayList<EditText> rowinfo = AddWorkoutClass.containerFortestxArray.get(i);

            for (int j = 0; j < 5; j++) {
                System.out.println("we here");
                EditText textField = rowinfo.get(j);
                String ys = textField.getText().toString();
                if (j == 0) {
                    try {
                        ex.setName(ys);
                    } catch (Exception a) {
                        ex.setName(" ");
                    }
                }
                if (j == 1) {
                    try {
                        ex.setReps(ys);
                    } catch (Exception a) {
                        ex.setReps("0");
                    }
                }
                if (j == 2) {
                    try {
                        ex.setSets(ys);
                    } catch (Exception a) {
                        ex.setSets("1");
                    }
                }
                if (j == 3) {
                    try {
                        ex.setWeight(ys);
                    } catch (Exception a) {
                        ex.setWeight("1");
                    }
                }
                if (j == 4) {
                    try {
                        ex.setRest(ys);
                    } catch (Exception a) {
                        ex.setRest("0");
                    }
                }
            }
            ex.setVolume(ex.getReps(), ex.getSets());
            weightVol = weightVol + calcWeightVol(ex.getVolume(), ex.getWeight());
            vol = vol + ex.getVolume();
        }

        AddWorkoutClass.numberOfExercises++;
        AddWorkoutClass.thisWorkout.setVolume(vol);
        AddWorkoutClass.thisWorkout.setWeightVolume(weightVol);
        AddWorkoutClass.volumeLabel.setText("Total volume: " + weightVol + "kg");
    }

    public Double calcWeightVol(int exVol, Double weight) {
         Double result = exVol * weight;
        return result;
    }

    public void makeButtonAndTextRowParams(LinearLayout.LayoutParams params) {
        params.leftMargin = 10;
        params.rightMargin = 10;
        params.topMargin = 20;
        params.bottomMargin = 20;
        params.gravity = Gravity.CENTER_HORIZONTAL;
    }
}

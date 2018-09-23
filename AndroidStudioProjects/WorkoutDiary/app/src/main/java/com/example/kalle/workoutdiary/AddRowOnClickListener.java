package com.example.kalle.workoutdiary;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

class AddRowOnClickListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        System.out.println("we here");
        LinearLayout.LayoutParams textRowParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        makeButtonAndTextRowParams(textRowParams);

        int vol = 0;
        MainActivity.rowList.add(new LinearLayout(v.getContext()));
        LinearLayout textFieldRows = MainActivity.rowList.get(MainActivity.numberOfExercises);

        TextFieldMaker.makeTextFields(textFieldRows, v);
        MainActivity.exerciseRowContainer.addView(textFieldRows, textRowParams);

        Workout.savedExercisez.clear();

        for (int i = 0; i < MainActivity.numberOfExercises; i++ ) {

            Workout.savedExercisez.add(new Exercise());
            Exercise ex = MainActivity.thisWorkout.savedExercisez.get(i);
            System.out.println(i);

            ArrayList<EditText> rowinfo = MainActivity.containerFortestxArray.get(i);

            for (int j = 0; j < 5; j++) {
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
            vol = vol + ex.getVolume();
        }

        MainActivity.numberOfExercises++;
        MainActivity.thisWorkout.setVolume(vol);
        String volString = Integer.toString(vol);
        MainActivity.volumeLabel.setText("Total Volume: " + MainActivity.thisWorkout.getVolume());
    }

    public void makeButtonAndTextRowParams(LinearLayout.LayoutParams params) {
        params.leftMargin = 10;
        params.rightMargin = 10;
        params.topMargin = 20;
        params.bottomMargin = 20;
        params.gravity = Gravity.CENTER_HORIZONTAL;
    }
}

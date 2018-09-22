package com.example.kalle.workoutdiary;

import java.io.Serializable;
import java.util.ArrayList;

public class Workout implements Serializable {
    static ArrayList<Exercise> savedExercisez = new ArrayList<Exercise>();
    private int volume;
    private ArrayList<Exercise> theSavedExercisesList;

    public void setTheSavedExercisesList(ArrayList<Exercise> listOfExercises) {
        theSavedExercisesList = listOfExercises;
    }

    public ArrayList<Exercise> getSavedExercisesList() {
        return theSavedExercisesList;
    }
    //static int numWorkouts;

    public void setVolume(int vol) {
        volume = vol;
    }

    public int getVolume() {
        return volume;
    }

}

package com.example.kalle.workoutdiary;

import java.util.ArrayList;

public class TedtWorkout {
    private int id;
    private ArrayList<Exercise> exercisesList;

    public TedtWorkout(int ids, ArrayList<Exercise> exList) {
        this.exercisesList = exList;
        this.id = ids;
    }

}

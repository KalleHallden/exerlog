package com.example.kalle.workoutdiary;

import android.content.Context;
import android.view.View;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;

public class Workout implements Serializable {
    static ArrayList<Exercise> savedExercisez = new ArrayList<Exercise>();
    private int volume;
    //static int numWorkouts;

    public void setVolume(int vol) {
        volume = volume + vol;
    }

    public int getVolume() {
        return volume;
    }

}

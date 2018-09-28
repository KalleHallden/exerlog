package com.example.kalle.workoutdiary;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SaveWorkout {

    static int n;

    static String filepath = "Workout";
    static Double[] volumes;

    public static int checkExistingFiles(Context context) {
        n = 0;
        int y = 1;
                 for (int i = 0; i < y; i++) {
                     try {
                         FileInputStream fi = context.openFileInput("Workout" + n);
                         ObjectInputStream oi = new ObjectInputStream(fi);
                         y++;
                         n++;
                     } catch (Exception e) {

                         System.out.println("Done checking existing files");
                     }
                 }
         volumes = new Double[n];
         return n;
    }

    public void WriteObjectToFile(Workout workout, Context context) {
        workout.setVolume(CopyOfWorkoutActivity.vol);
        workout.setTheSavedExercisesList(Workout.savedExercisez);

        System.out.println("hey");
        int num = checkExistingFiles(context);

        System.out.println("Amount of workouts " + num);

        filepath = "Workout" + num;

        try {
            FileOutputStream fileOut = context.openFileOutput(filepath, Context.MODE_PRIVATE);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(workout);
            objectOut.close();
            fileOut.close();
            System.out.println("The Object  was succesfully written to a file");
            System.out.println(filepath);
        } catch (Exception ex) {
            System.out.println("Couldnt find it");
            ex.printStackTrace();
        }
        //Workout.savedExercisez.clear();
    }

    public void saveSpecific(int id, Context context, Workout workout) {
        String fileName = "Workout" + id;
        workout.setVolume(CopyOfWorkoutActivity.vol);
        workout.setTheSavedExercisesList(Workout.savedExercisez);

        try {
            FileOutputStream fileOut = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(workout);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");
            System.out.println(fileName);
            fileOut.close();
        } catch (Exception ex) {
            System.out.println("Couldnt find it");
            ex.printStackTrace();
        }
        //Workout.savedExercisez.clear();

    }

    public Workout openWorkout(int id, Context context) {
        Workout workout;
        int volume = 0;
        Double volumeWeight = 0.0;
        try {
            FileInputStream fi = context.openFileInput("Workout" + id);
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            workout = (Workout) oi.readObject();
            System.out.println("It worked");

            oi.close();
            fi.close();

            Workout.savedExercisez = workout.getSavedExercisesList();

            for (int i = 0; i < workout.getSavedExercisesList().size(); i++){
                System.out.println("Hey " + i);
                Exercise e = Workout.savedExercisez.get(i);
                e.setVolume(e.getReps(), e.getSets());
                volume = volume + e.getVolume();
                volumeWeight = volumeWeight + calcWeightVol(e.getVolume(), e.getWeight());

                System.out.println("Volume " + volume);
            }

            workout.setVolume(volume);
            volumes[id] = volumeWeight;

            return workout;
        } catch (Exception s) {
            System.out.println("It didn't work");
            System.out.println("This the exception: ");
            s.printStackTrace();

         return workout = new Workout();
        }
    }

    public Double calcWeightVol(int exVol, Double weight) {
        Double result = exVol * weight;
        return result;
    }






}

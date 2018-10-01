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
    static Workout[] listOfWorkouts;
    static WorkoutLog log = new WorkoutLog();
    static boolean filesHaveBeenChecked;

    public static int checkExistingFiles(Context context) {
       // System.out.println("We are checking files and currently " + listOfWorkouts.length + " workouts exist");
        //n = 0;
        int y = 1;

        System.out.println(" ");
        if (!filesHaveBeenChecked) {
            try {
                FileInputStream fi = context.openFileInput("Workout");
                ObjectInputStream oi = new ObjectInputStream(fi);
                y++;
            } catch (Exception e) {
                System.out.println("Doesn't exist");
            }

            try {
                FileInputStream fi = context.openFileInput("Workout");
                ObjectInputStream oi = new ObjectInputStream(fi);

                // Read objects
                log = (WorkoutLog) oi.readObject();
                System.out.println("This is the size of n: " + (log.workouts.size()));
                n = log.workouts.size();

            } catch (Exception e) {

            }
            filesHaveBeenChecked = true;
        } else {
            n = log.workouts.size();
            filesHaveBeenChecked = true;
        }

        System.out.println("This is n " + n);

         volumes = new Double[n];
         return n;
    }

    public void deleteSpecific(Context context, String identifier) {
        for (int i = 0; i < n; i++){
            if (log.workouts.get(i).getDate() == identifier) {
                log.workouts.remove(i);
                WriteObjectToFile(log, context);
            }
        }
    }

    public void WriteObjectToFile(WorkoutLog workout, Context context) {
        filesHaveBeenChecked = true;

        System.out.println("hey");
        int num = checkExistingFiles(context);

        System.out.println("Amount of workouts " + workout.workouts.size());

        filepath = "Workout";

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

    public void saveSpecific(Context context, WorkoutLog workout) {
        String fileName = "Workout";

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
            FileInputStream fi = context.openFileInput("Workout");
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            log = (WorkoutLog) oi.readObject();
            System.out.println("It worked");
            workout = log.workouts.get(id);

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

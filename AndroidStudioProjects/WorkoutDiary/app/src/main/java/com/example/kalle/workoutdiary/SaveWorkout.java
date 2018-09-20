package com.example.kalle.workoutdiary;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveWorkout {

    static int n;

    static String filepath = "Workout";

    public void WriteObjectToFile(Workout workout, Context context) {
        n = 0;
        System.out.println("hey");
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

        filepath = "Workout" + n;

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
    }

    public void saveSpecific(int id, Context context, Workout workout) {
        String fileName = "Workout" + id;
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

    }

    public Workout openWorkout(int id, Context context) {
        Workout workout;
        try {
            FileInputStream fi = context.openFileInput("Workout" + id);
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
           workout = (Workout) oi.readObject();

            System.out.println(workout.getVolume());
            System.out.println("It worked");

            oi.close();
            fi.close();
            return workout;
        } catch (Exception s) {
            System.out.println("It didn't work");
            System.out.println("This the exception: ");
            s.printStackTrace();
         return workout = new Workout();
        }
    }
}

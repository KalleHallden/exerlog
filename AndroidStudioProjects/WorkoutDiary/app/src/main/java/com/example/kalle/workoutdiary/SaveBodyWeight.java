package com.example.kalle.workoutdiary;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SaveBodyWeight {
    static int n;
    static ArrayList<BodyStats> list = new ArrayList<BodyStats>();

    static String filepath = "BodyStats";

    public static int checkExistingFiles(Context context) {
        //list.clear();
        n = 0;
        int y = 1;
        for (int i = 0; i < y; i++) {
            try {
                FileInputStream fi = context.openFileInput("BodyStats" + n);
                ObjectInputStream oi = new ObjectInputStream(fi);
                y++;
                n++;
                list.add(new BodyStats());
            } catch (Exception e) {

                System.out.println("Done checking existing files");
            }
        }
        return n;
    }

    public void WriteObjectToFile(BodyStats bodystats, Context context) {

        System.out.println("bodystatshey");
        int num = checkExistingFiles(context);

        System.out.println("Amount of bodystats saved " + num);

        filepath = "BodyStats" + num;

        try {
            FileOutputStream fileOut = context.openFileOutput(filepath, Context.MODE_PRIVATE);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(bodystats);
            objectOut.close();
            fileOut.close();
            System.out.println("The Object  was succesfully written to a file");
            System.out.println(filepath);
        } catch (Exception ex) {
            System.out.println("Couldnt find it");
            ex.printStackTrace();
        }
    }

    public void saveSpecific(int id, Context context, BodyStats bodystats) {
        String fileName = "BodyStats" + id;

        try {
            FileOutputStream fileOut = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(bodystats);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");
            System.out.println(fileName);
            fileOut.close();
        } catch (Exception ex) {
            System.out.println("Couldnt find it");
            ex.printStackTrace();
        }
    }

    public BodyStats openBodyStat(int id, Context context) {
        BodyStats bodystats;
        int volume = 0;
        try {
            FileInputStream fi = context.openFileInput("Workout" + id);
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            bodystats = (BodyStats) oi.readObject();
            System.out.println("It worked");

            System.out.println("This is a bodyweight: " + bodystats.getBodyWeight());

            oi.close();
            fi.close();

            return bodystats;
        } catch (Exception s) {
            System.out.println("It didn't work");
            System.out.println("This the exception: ");
            s.printStackTrace();

            return bodystats = new BodyStats();
        }
    }

}

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

    static Double[] bodyweightList;
    static Double[] neckSizeList;
    static Double[] wristSizeList;
    static Double[] chestSizeList;
    static Double[] bicepSizeList;
    static Double[] waistSizeList;
    static Double[] thighSizeList;
    static Double[] calfSizeList;

    static BodyStatLog log = new BodyStatLog();
    static boolean filesHaveBeenChecked;

    static String filepath = "BodyStats";

    public static int checkExistingFiles(Context context) {
        //list.clear();
        n = 0;
        int y = 1;
        if (!filesHaveBeenChecked) {
            try {
                FileInputStream fi = context.openFileInput("BodyStats");
                ObjectInputStream oi = new ObjectInputStream(fi);
                y++;
                n++;
                list.add(new BodyStats());
            } catch (Exception e) {

                System.out.println("Done checking existing files");
            }
            try {
                FileInputStream fi = context.openFileInput("BodyStats");
                ObjectInputStream oi = new ObjectInputStream(fi);

                // Read objects
                log = (BodyStatLog) oi.readObject();
                System.out.println("This is the size of n: " + (log.bodystatsList.size()));
                n = log.bodystatsList.size();

            } catch (Exception e) {

            }
            filesHaveBeenChecked = true;
        } else {
            n = log.bodystatsList.size();
            filesHaveBeenChecked = true;
        }

        bodyweightList = new Double[n];
        neckSizeList = new Double[n];
        waistSizeList = new Double[n];
        bicepSizeList = new Double[n];
        chestSizeList = new Double[n];
        thighSizeList = new Double[n];
        wristSizeList = new Double[n];
        calfSizeList = new Double[n];

        return n;
    }

    public void deleteSpecific(Context context, String identifier) {
        for (int i = 0; i < n; i++){
            if (log.bodystatsList.get(i).getDate() == identifier) {
                log.bodystatsList.remove(i);
                WriteObjectToFile(log, context);
            }
        }
    }

    public void WriteObjectToFile(BodyStatLog log, Context context) {
        filesHaveBeenChecked = true;
        System.out.println("bodystatshey");
        int num = checkExistingFiles(context);

        System.out.println("Amount of bodystats saved " + num);

        filepath = "BodyStats";

        try {
            FileOutputStream fileOut = context.openFileOutput(filepath, Context.MODE_PRIVATE);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(log);
            objectOut.close();
            fileOut.close();
            System.out.println("The Object  was succesfully written to a file");
            System.out.println(filepath);
        } catch (Exception ex) {
            System.out.println("Couldnt find it");
            ex.printStackTrace();
        }
    }

    public void saveSpecific(Context context, BodyStatLog bodystats) {
        String fileName = "BodyStats";

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
            FileInputStream fi = context.openFileInput("BodyStats");
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            log = (BodyStatLog) oi.readObject();
            System.out.println("It worked");
            bodystats = log.bodystatsList.get(id);

            System.out.println("This is a bodyweight: " + bodystats.getBodyWeight());

            oi.close();
            fi.close();

            bodyweightList[id] = bodystats.getBodyWeight();
            neckSizeList[id] = bodystats.getNeckSize();
            chestSizeList[id] = bodystats.getChestSize();
            wristSizeList[id] = bodystats.getWristSize();
            waistSizeList[id] = bodystats.getWaistSize();
            bicepSizeList[id] = bodystats.getBicepsSize();
            calfSizeList[id] = bodystats.getCalfSize();
            thighSizeList[id] = bodystats.getThighSize();

            return bodystats;
        } catch (Exception s) {
            System.out.println("It didn't work");
            System.out.println("This the exception: ");
            s.printStackTrace();

            return bodystats = new BodyStats();
        }
    }

}

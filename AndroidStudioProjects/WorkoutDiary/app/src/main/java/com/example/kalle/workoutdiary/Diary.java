package com.example.kalle.workoutdiary;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import android.view.View;

public class Diary {
    int count;
    static ArrayList<Workout> workoutList = new ArrayList<Workout>();
    static int numWorkouts;

    public void openWorkouts(Context v) {
        numWorkouts = 0;
        workoutList.clear();
        System.out.println("This is numWorkouts: " + (numWorkouts));

        String end = " ";
        int y = 1;
        for (int i = 0; i < y; i++) {
            String file1 = "Workout" + (numWorkouts);
            FileInputStream fis = null;
            try {
                fis = v.openFileInput(file1);
                System.out.println("We are still creating files");
                System.out.println("This is the current file you're creating: " + file1);
                workoutList.add(new Workout());
                y++;
                numWorkouts++;
                System.out.println("Workout list size: " + workoutList.size());
            } catch (Exception workoutNoExist) {
                System.out.println("No more workouts");
            }
        }
    }

    public void openWorkout(Context v) {
                      numWorkouts = 0;
                      workoutList.clear();
                      System.out.println("This is numWorkouts: " + (numWorkouts));

                      String end = " ";
                      int y = 1;
                      for (int i = 0; i < y; i++) {                                                                                            
                          String file1 = "Workout" + (numWorkouts);
                          FileInputStream fis = null;
                          try {
                              fis = v.openFileInput(file1);
                              System.out.println("We are still creating files");
                              System.out.println("This is the current file you're creating: " + file1);
                              workoutList.add(new Workout());
                              System.out.println("Workout list size: " + workoutList.size());

                               InputStreamReader isr = new InputStreamReader(fis);
                               BufferedReader bufferedReader = new BufferedReader(isr);
                               System.out.println("we are here");

                               try {
                                   StringBuffer sb = new StringBuffer();
                                   String line;
                                   System.out.println("runs");
                                   line = bufferedReader.readLine();
                                   System.out.println("runs  " + line);

                                   while ((line = bufferedReader.readLine()) != null) {
                                       System.out.println("runs2");
                                       sb.append(line);
                                       String text = (String) line;
                                       System.out.println("This is the total lines in this new one: " + text);

                                       String[] data = text.split("END");


                                       Workout thisWorkout = workoutList.get(i);
                                       thisWorkout.savedExercisez = new ArrayList<Exercise>();

                                       for (int d = 0; d < data.length; d++) {
                                           System.out.println("This is current: " + data[d]);
                                           String dat = data[d];
                                           String[] info = dat.split("SPLIT");



                                           thisWorkout.savedExercisez.add(new Exercise());
                                           Exercise exer = thisWorkout.savedExercisez.get(d);

                                           for (int o = 0; o < 5; o++) {
                                               if (o == 0) {
                                                   try {
                                                       exer.setName(info[o]);
                                                   } catch (Exception e) {
                                                       exer.setName(" ");
                                                   }
                                                   System.out.println("This was your name: " + exer.getName());
                                               } if (o == 1) {
                                                   try {
                                                       exer.setReps(info[o]);
                                                   } catch (Exception e) {
                                                       exer.setReps("0");
                                                   }
                                                   System.out.println("This was your reps: " + exer.getReps());
                                               }
                                               if (o == 2) {
                                                   try {
                                                       exer.setSets(info[o]);
                                                   } catch (Exception e) {
                                                       exer.setSets("1");
                                                   }
                                                   System.out.println("This was your sets: " + exer.getSets());
                                               } if (o == 3) {
                                                   try {
                                                       exer.setWeight(info[o]);
                                                   } catch (Exception e) {
                                                       exer.setWeight("0");
                                                   }
                                                   System.out.println("This was your weight: " + exer.getWeight());
                                               } if (o == 4) {
                                                   try {
                                                       exer.setRest(info[o]);
                                                   } catch (Exception e) {
                                                       exer.setRest("0");
                                                   }
                                                   System.out.println("This was your rest: " + exer.getRest());
                                               }

                                           }


                                       }


                                   }
                                   System.out.println("this is how many workouts have been created " + workoutList.size());

                                   System.out.println("");
                                   isr.close();
                                   System.out.println("");
                               } catch (Exception e) {
                                   System.out.println("Exception");

                               }
                              numWorkouts++;
                              y++;
                          } catch (FileNotFoundException e) {
                              System.out.println("this is the end of files");
                              e.printStackTrace();
                              end = "END";
                          }



                      }


    }

    public static  Workout openSpecificWorkout(Context context, int workoutID, Workout workout) {
        // The name of the file to open.
        String fileName = "Workout" + (workoutID);

        // This will reference one line at a time
        String line = "";
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("file does not exist: " + fileName);
        }


        try {
            InputStream is = context.openFileInput(fileName);
            // FileReader reads text files in the default encoding.
            InputStreamReader fileReader =
                    new InputStreamReader(is);
            if (is != null) {
                System.out.println("Not equal to null");
            } else {
                System.out.println("Equals null");
            }

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            line = bufferedReader.readLine();
            System.out.println("Line: " + line);


            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(Exception ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
        return workout;
    }


    public static void save(Workout workout) {
          
    }


    public static Workout openSpecificWorkout1(View v, int numberOfWokrout) {

        System.out.println("We got in");
        //Workout.savedExercisez.clear();
           String file1 = "Workout" + numberOfWokrout;
           Workout thisWorkout = new Workout();
         FileInputStream fis = null;
         try {
             fis = v.getContext().openFileInput(file1);
             System.out.println("File found");

             //try {
                 InputStreamReader isr = new InputStreamReader(fis);
                 BufferedReader bufferedReader = new BufferedReader(isr);
                 StringBuffer sb = new StringBuffer();
                 String line;
                 line = bufferedReader.readLine();

                 System.out.println("here " + line);

                 while ((line = bufferedReader.readLine()) != null) {
                     System.out.println("here");
                     sb.append(line);
                     String text = (String) line;
                     System.out.println("This is the total lines int this new one: " + text);

                     String[] data = text.split("END");

                     thisWorkout = workoutList.get(numberOfWokrout);
                     thisWorkout.savedExercisez = new ArrayList<Exercise>();

                     for (int d = 0; d < data.length; d++) {
                         System.out.println("This is current: " + data[d]);
                         String dat = data[d];
                         String[] info = dat.split("SPLIT");


                         thisWorkout.savedExercisez.add(new Exercise());
                         Exercise exer = thisWorkout.savedExercisez.get(d);

                         for (int o = 0; o < 5; o++) {
                             if (o == 0) {
                                 try {
                                     exer.setName(info[o]);
                                 } catch (Exception e) {
                                     exer.setName(" ");
                                 }
                                 System.out.println("This was your name: " + exer.getName());
                             }
                             if (o == 1) {
                                 try {
                                     exer.setReps(info[o]);
                                 } catch (Exception e) {
                                     exer.setReps("0");
                                 }
                                 System.out.println("This was your reps: " + exer.getReps());
                             }
                             if (o == 2) {
                                 try {
                                     exer.setSets(info[o]);
                                 } catch (Exception e) {
                                     exer.setSets("1");
                                 }
                                 System.out.println("This was your sets: " + exer.getSets());
                             }
                             if (o == 3) {
                                 try {
                                     exer.setWeight(info[o]);
                                 } catch (Exception e) {
                                     exer.setWeight("0");
                                 }
                                 System.out.println("This was your weight: " + exer.getWeight());
                             }
                             if (o == 4) {
                                 try {
                                     exer.setRest(info[o]);
                                 } catch (Exception e) {
                                     exer.setRest("0");
                                 }
                                 System.out.println("This was your rest: " + exer.getRest());
                             }

                         }


                     }


                 }
                 System.out.println("this is how many exercises that have been created " + thisWorkout.savedExercisez.size());

                 System.out.println("");
                 isr.close();
                 System.out.println("");
             //} catch (Exception e) {
                 //System.out.println("Exception");

             //}
         }  catch (Exception e) {
              System.out.println("File not found " + file1);
         }
           return thisWorkout;

    }

        public static void saveWorkout(View v) {
            //This is where you save the workout
            workoutList.clear();
            String file1 = "Workout0";

            int x = 0;
            String end = " ";
            int y = 1;
            for (int i = 0; i < y; i++) {
                file1 = "Workout" + (x);
                FileInputStream fis = null;
                try {
                    fis = v.getContext().openFileInput(file1);
                    System.out.println("This is the current file you're creating: " + file1);
                    numWorkouts++;
                    workoutList.add(new Workout());
                    System.out.println("Size " + workoutList.size());
                    x++;
                    y++;
                } catch (FileNotFoundException e) {
                    System.out.println("this is the end of files");
                    e.printStackTrace();
                    end = "END";
                }
            }
            System.out.println(file1);

            if (end.equals("END")) {
                FileInputStream fis = null;
                try {
                    fis = v.getContext().openFileInput(file1);
                    System.out.println("Right now you are at Workout" + x);
                    file1 = "Workout" + x;
                } catch (Exception e) {
                    System.out.println("No more files");
                }

                try {
                    FileOutputStream os = v.getContext().openFileOutput(file1, Context.MODE_PRIVATE);
                    System.out.println("Workout number " + file1);

                    System.out.println("Creating bufferedwriter");
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(os));

                    //try {
                        System.out.println("Opening fileoutput");

                        workoutList.add(new Workout());
                        Workout workout = workoutList.get(x);
                        System.out.println(x);
                        System.out.println(workout.savedExercisez.size());


                        for (int i = 0; i < workout.savedExercisez.size(); i++) {
                            System.out.println("Writing files " + i);
                            Exercise exer = workout.savedExercisez.get(i);
                            String name = exer.getName();
                            String reps = Integer.toString(exer.getReps());
                            String sets = Integer.toString(exer.getSets());
                            String weight = Integer.toString(exer.getWeight());
                            String rest = Integer.toString(exer.getRest());
                            String split = "SPLIT";
                            String stopper = "END";

                            out.write(name);
                            out.write(split);
                            out.write(reps);
                            out.write(split);
                            out.write(sets);
                            out.write(split);
                            out.write(weight);
                            out.write(split);
                            out.write(rest);
                            out.write(split);
                            out.write(stopper);

                        }


                    //} catch (Exception e) {
                     //   e.printStackTrace();
                    //}
                    out.close();
                    os.close();
                    out.flush();
                    os.flush();
                    System.out.println("Done");
                } catch (Exception fileForOutputstreamnotfound) {
                    System.out.println("fileForOutputstreamnotfound");
                }

            }
        }



}
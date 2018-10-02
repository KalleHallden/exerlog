package com.example.kalle.workoutdiary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.xml.parsers.SAXParser;

public class BodyStatActivity extends AppCompatActivity {

    Button saveButton;
    Button deleteButton;

    static LinearLayout exerciseRowContainer;
    ScrollView rowScroller;
    static TextView volumeLabel;
    static ArrayList<LinearLayout> rowList;
    ArrayList<Exercise> exerciseList;
    static ArrayList<ArrayList> containerFortestxArray;
    static ArrayList<EditText> textFieldArray;
    static Workout thisWorkout;
    static int numberOfExercises;
    static int vol;
    BodyStats bs;
    BodyStats bsthree;
    BodyStats bsthe;
    String deleteDate;

    LinearLayout row;
    LinearLayout topBar;
    LinearLayout totalLayout;
    LinearLayout testLayout;
    LinearLayout layoutTop;
    LinearLayout layoutTop2;
    LinearLayout layoutTop3;
    LinearLayout labelrow;
    LinearLayout volumeRow;
    LinearLayout divider;
    LinearLayout textFieldRows;
    LinearLayout rowofTextAndLabels;

    ImageButton backButton;

    LinearLayout.LayoutParams buttonParams;
    LinearLayout.LayoutParams bottomPara;

    GridLayout grid;

    int thirdWidth;
    int width;
    int height;


    static boolean newEntryClicked;

    View view;
    int darkgrey = Color.rgb(30, 30, 30);


    ArrayList<EditText> textfieldArray;

    public ArrayList<Exercise> savedExercises = new ArrayList<Exercise>();
    ArrayList<Workout> savedWorkouts = new ArrayList<Workout>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
    }



    public void setUp() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(BottomNaviClass.black);

        view = new View(this);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        thirdWidth = (int) (width * 0.333333);

        numberOfExercises = 0;
        exerciseList = new ArrayList<Exercise>();
        containerFortestxArray = new ArrayList<ArrayList>();
        rowList = new ArrayList<LinearLayout>();

        System.out.println("1 worked");

        layoutParams();


    }

    public void layoutParams() {
        // Button Parameter creator
        buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        SetUp.makeButtonAndTextRowParams(buttonParams);

        bottomPara = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, Gravity.BOTTOM);
        System.out.println("2 worked");
        setupLayout();
    }

    public void setupLayout() {
        testLayout = new LinearLayout(this);
        testLayout.setBackgroundColor(BottomNaviClass.grey);
        totalLayout = new LinearLayout(this);
        totalLayout.setOrientation(LinearLayout.VERTICAL);

        //Make rows for labels, exercise rows and button
        row = new LinearLayout(this);
        row.setOrientation(LinearLayout.VERTICAL);

        // Make layout for labels
        labelrow = new LinearLayout(this);
        volumeRow = new LinearLayout(this);

        grid = new GridLayout(this);

        layoutTop = new LinearLayout(this);
        layoutTop2 = new LinearLayout(this);
        layoutTop3 = new LinearLayout(this);

        topBar = new LinearLayout(this);

        exerciseRowContainer = new LinearLayout(this);

        rowScroller = new ScrollView(this);

        divider = new LinearLayout(this);
        System.out.println("3 worked");
        setLayouts();

    }

    public void setLayouts() {

        topBar.setBackgroundColor(BottomNaviClass.lightBlack);
        topBar.setMinimumHeight(100);

        layoutTop2.setMinimumHeight(100);
        layoutTop2.setMinimumWidth(thirdWidth);
        layoutTop3.setMinimumHeight(100);
        layoutTop3.setMinimumWidth(thirdWidth);
        layoutTop.setMinimumHeight(100);
        layoutTop.setMinimumWidth(thirdWidth);

        grid.setOrientation(GridLayout.HORIZONTAL);

        rowScroller.setLayoutParams(new RelativeLayout.LayoutParams(
                ScrollView.LayoutParams.FILL_PARENT, 800));

        exerciseRowContainer.setOrientation(LinearLayout.VERTICAL);
        System.out.println("4 worked");
        addButtons();
    }

    public void addViews() {

        // Make initial row of textfields and increment number of rows by 1 and add it to the exerciseRowContainer
        

        if (!newEntryClicked) {
            layoutTop.addView(saveButton);
            layoutTop3.addView(deleteButton);
        } else {
            layoutTop.addView(backButton);
            layoutTop3.addView(saveButton);
        }


        grid.addView(layoutTop);
        grid.addView(layoutTop2);
        grid.addView(layoutTop3);
        topBar.addView(grid);

        textfieldArray = new ArrayList<EditText>();
        makeTextFields();

        for (int i = 0; i < 8; i++) {
            TextView label = new TextView(this);
            if (i == 0){
                label.setText("Body Weight:  ");
            }
            if (i == 1){
                label.setText("Bicep Size:     ");
            }
            if (i == 2){
                label.setText("Neck Size:     ");
            }
            if (i == 3){
                label.setText("Wrist Size:     ");
            }
            if (i == 4){
                label.setText("Chest Size:     ");
            }
            if (i == 5){
                label.setText("Waist Size:     ");
            }
            if (i == 6){
                label.setText("Thigh Size:     ");
            }
            if (i == 7){
                label.setText("Calf Size:       ");
            }



            LinearLayout rowofTextAndLabels = new LinearLayout(this);
            rowofTextAndLabels.addView(label, buttonParams);
            rowofTextAndLabels.addView(textfieldArray.get(i));
            exerciseRowContainer.addView(rowofTextAndLabels, buttonParams);
        }

        numberOfExercises = numberOfExercises + 1;

        //rowScroller.addView(exerciseRowContainer);
        System.out.println("6 worked");
        completeSetups();

    }

    public void addButtons() {

        saveButton = new Button(this);
        saveButton.setText("✓");
        saveButton.setTextSize(30);
        saveButton.setTextColor(BottomNaviClass.red);
        saveButton.setBackgroundColor(topBar.getSolidColor());
        saveButton.setOnClickListener(new SaveBodyStatListener());

        deleteButton = new Button(this);
        deleteButton.setBackgroundColor(topBar.getSolidColor());

        if (!newEntryClicked) {
            deleteButton.setText("DELETE");
            deleteButton.setTextSize(30);
            deleteButton.setTextColor(BottomNaviClass.red);
            deleteButton.setOnClickListener(new DeleteBodyStatListener());
        } else {
            backButton = new ImageButton(this);
            backButton.setImageResource(R.drawable.back_icon);
            backButton.setMinimumWidth(200);
            backButton.setColorFilter(BottomNaviClass.red);
            backButton.setBackgroundColor(BottomNaviClass.lightBlack);
            backButton.setOnClickListener(new GoBackOnClickListener());
        }

        System.out.println("5 worked");
        addViews();
    }

    public void completeSetups() {

        SetUp.makeLinearLayoutParamsTopBar(totalLayout);
        System.out.println("7 worked");
        makeContentView();
    }

    public void makeContentView() {

        row.addView(topBar);
        row.addView(exerciseRowContainer);

        row.setLayoutParams(bottomPara);

        totalLayout.addView(row);
        totalLayout.addView(divider);
        testLayout.addView(totalLayout, bottomPara);
        setContentView(testLayout);
        System.out.println("8 worked");

    }

    public ArrayList<EditText> makeTextFields() {
            if (newEntryClicked == false) {
                for (int i = 0; i < 8; i++) {
                    textfieldArray.add(new EditText(getBaseContext()));
                    EditText textfield = textfieldArray.get(i);
                    MyTextField.makeTextField(textfield, (width / 2));
                    textfield.setMinimumWidth(width/2);
                    textfield.setMinHeight(100);
                    textfield.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    textfield.setBackgroundColor(BottomNaviClass.red);
                    textfield.setTextSize(20);
                }
                textfieldArray = retrievePrevious(BodyStatClass.identify, textfieldArray);

            } else {
                for (int i = 0; i < 8; i++) {
                    textfieldArray.add(new EditText(getBaseContext()));
                    EditText textfield = textfieldArray.get(i);
                    MyTextField.makeTextField(textfield, (width / 2));
                    textfield.setMinimumWidth(width/2);
                    textfield.setMinHeight(100);
                    textfield.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    textfield.setBackgroundColor(BottomNaviClass.red);
                    textfield.setTextSize(20);
                }

            }


      return textfieldArray;
    }

     class GoBackOnClickListener implements  View.OnClickListener {
                @Override
                 public void onClick(View v) {
                    SaveBodyWeight.checkExistingFiles(v.getContext());
                    Context context = v.getContext();
                    Intent intent = new Intent(context, BottomNaviClass.class);
                    BottomNaviClass.identifier = 4;
                    context.startActivity(intent);
                    BottomNaviClass.inWeightPage = true;
                }
        }


    class SaveBodyStatListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            if (newEntryClicked) {
                bs = new BodyStats();

                for (int i = 0; i < 8; i++) {
                    EditText textfield = textfieldArray.get(i);
                    String text = String.valueOf(textfield.getText());
                    if (i == 0) {
                        try {
                            bs.setBodyWeight(text);
                        } catch (Exception noTextFound) {
                            bs.setBodyWeight(retrievePreviousSpec(i));
                        }
                    }
                    if (i == 1) {
                        try {
                            bs.setBicepsSize(text);
                        } catch (Exception noTextFound) {
                            bs.setBicepsSize(retrievePreviousSpec(i));
                        }
                    } if (i == 2) {
                        try {
                            bs.setNeckSize(text);
                        } catch (Exception noTextFound) {
                            bs.setNeckSize(retrievePreviousSpec(i));
                        }
                    }  if (i == 3) {
                        try {
                            bs.setWristSize(text);
                        } catch (Exception noTextFound) {
                            bs.setWristSize(retrievePreviousSpec(i));
                        }
                    }  if (i == 4) {
                        try {
                            bs.setChestSize(text);
                        } catch (Exception noTextFound) {
                            bs.setChestSize(retrievePreviousSpec(i));
                        }
                    }   if (i == 5) {
                        try {
                            bs.setWaistSize(text);
                        } catch (Exception noTextFound) {
                            bs.setWaistSize(retrievePreviousSpec(i));
                        }
                    }  if (i == 6) {
                        try {
                            bs.setThighSize(text);
                        } catch (Exception noTextFound) {
                            bs.setThighSize(retrievePreviousSpec(i));
                        }
                    } if (i == 7) {
                        try {
                            bs.setCalfSize(text);
                        } catch (Exception noTextFound) {
                            bs.setCalfSize(retrievePreviousSpec(i));
                        }
                    }

                }
                Long tsLong = System.currentTimeMillis()/1000;
                String timeMilli = tsLong.toString();
                String ts = getDate(tsLong).toString();
                String[] s = ts.split(":00 GMT");
                String s2 = s[1];
                String[] r = s2.split(":00");
                String date = s[0] + r[1];

                bs.setDate(date);
                SaveBodyWeight test = new SaveBodyWeight();
                SaveBodyWeight.log.bodystatsList.add(bs);
                test.WriteObjectToFile(SaveBodyWeight.log, v.getContext());
                Context context = v.getContext();
                Intent intent = new Intent(context, BottomNaviClass.class);
                BottomNaviClass.identifier = 4;
                context.startActivity(intent);
                BottomNaviClass.inWeightPage = true;


            } else {
                System.out.println("Saving re");

                SaveBodyWeight opener = new SaveBodyWeight();
                SaveBodyWeight.log.bodystatsList.remove(BodyStatClass.identify);
                BodyStats bs2 = new BodyStats();

                SaveBodyWeight.checkExistingFiles(getBaseContext());

                for (int i = 0; i < 8; i++) {
                    EditText textfield = textfieldArray.get(i);
                    String text = String.valueOf(textfield.getText());
                    if (i == 0) {
                        try {
                            bs2.setBodyWeight(text);
                        } catch (Exception noTextFound) {
                            bs2.setBodyWeight(retrievePreviousSpec(i));
                        }
                    }
                    if (i == 1) {
                        try {
                            bs2.setBicepsSize(text);
                        } catch (Exception noTextFound) {
                            bs2.setBicepsSize(retrievePreviousSpec(i));
                        }
                    } if (i == 2) {
                        try {
                            bs2.setNeckSize(text);
                        } catch (Exception noTextFound) {
                            bs2.setNeckSize(retrievePreviousSpec(i));
                        }
                    }  if (i == 3) {
                        try {
                            bs2.setWristSize(text);
                        } catch (Exception noTextFound) {
                            bs2.setWristSize(retrievePreviousSpec(i));
                        }
                    }  if (i == 4) {
                        try {
                            bs2.setChestSize(text);
                        } catch (Exception noTextFound) {
                            bs2.setChestSize(retrievePreviousSpec(i));
                        }
                    }   if (i == 5) {
                        try {
                            bs2.setWaistSize(text);
                        } catch (Exception noTextFound) {
                            bs2.setWaistSize(retrievePreviousSpec(i));
                        }
                    }  if (i == 6) {
                        try {
                            bs2.setThighSize(text);
                        } catch (Exception noTextFound) {
                            bs2.setThighSize(retrievePreviousSpec(i));
                        }
                    } if (i == 7) {
                        try {
                            bs2.setCalfSize(text);
                        } catch (Exception noTextFound) {
                            bs2.setCalfSize(retrievePreviousSpec(i));
                        }
                    }

                }

                Long tsLong = System.currentTimeMillis()/1000;
                String timeMilli = tsLong.toString();
                String ts = getDate(tsLong).toString();
                String[] s = ts.split(":00 GMT");
                String s2 = s[1];
                String[] r = s2.split(":00");
                String date = s[0] + r[1];

                bs2.setDate(date);

                SaveBodyWeight.log.bodystatsList.add(BodyStatClass.identify ,bs2);



                SaveBodyWeight test = new SaveBodyWeight();

                test.saveSpecific(v.getContext(), SaveBodyWeight.log);

                layoutTop.removeAllViews();
                backButton = new ImageButton(v.getContext());
                layoutTop.addView(backButton);
                backButton.setImageResource(R.drawable.back_icon);
                backButton.setMinimumWidth(200);
                backButton.setColorFilter(BottomNaviClass.red);
                backButton.setBackgroundColor(BottomNaviClass.lightBlack);
                backButton.setOnClickListener(new GoBackOnClickListener());

            }

        }
    }


    public String retrievePreviousSpec(int which) {
        String info = "0.0";
        System.out.println("Retrieving specific");
        SaveBodyWeight opener = new SaveBodyWeight();

        SaveBodyWeight.checkExistingFiles(getBaseContext());
        bsthree = opener.openBodyStat((SaveBodyWeight.n -1), getBaseContext());


            if (which == 0) {
                try {
                    info = bsthree.getBodyWeight().toString();
                } catch (Exception e) {
                    System.out.println("Didn't work weight");
                }
            }
            if (which == 1) {
                try {
                    info = bsthree.getBicepsSize().toString();
                } catch (Exception e) {
                    System.out.println("Didn't work bicep");
                }
            }

            if (which == 2) {
                try {
                    info = bsthree.getNeckSize().toString();
                } catch (Exception e) {
                    System.out.println("Didn't work neck");
                }
            }

            if (which == 3) {
                try {
                    info = bsthree.getWristSize().toString();
                } catch (Exception e) {
                    System.out.println("Didn't work wrist");
                }
            }

            if (which == 4) {
                try {
                    info = bsthree.getChestSize().toString();
                } catch (Exception e) {
                    System.out.println("Didn't work chest");
                }
            }

            if (which == 5) {
                try {
                    info = bsthree.getWaistSize().toString();
                } catch (Exception e) {
                    System.out.println("Didn't work waist");
                }
            }
            if (which == 6) {
                try {
                    info = bsthree.getThighSize().toString();
                } catch (Exception e) {
                    System.out.println("Didn't work thigh");
                }
            }
            if (which == 7) {
                try {
                    info = bsthree.getCalfSize().toString();
                } catch (Exception e) {
                    System.out.println("Didn't work calf");
                }
            }
            else {
                info = "0.0";
            }


        return info;
    }

    String info = "0.0";
    public ArrayList<EditText> retrievePrevious(int which, ArrayList<EditText> texfieldArray) {
        SaveBodyWeight opener = new SaveBodyWeight();
        System.out.println("Recreating " + which);

        SaveBodyWeight.checkExistingFiles(getBaseContext());
        bsthe = opener.openBodyStat(which, getBaseContext());


        for (int i = 0; i < 8; i++) {
            if (i == 0) {
                try {
                    info = bsthe.getBodyWeight().toString();
                } catch (Exception e) {
                    System.out.println("Didn't work weight");
                }
                texfieldArray.get(i).setText(info);
            }
            if (i == 1) {
                try {
                    info = bsthe.getBicepsSize().toString();
                } catch (Exception e) {
                    System.out.println("Didn't work bicep");
                }
                texfieldArray.get(i).setText(info);
            }

            if (i == 2) {
                try {
                    info = bsthe.getNeckSize().toString();
                } catch (Exception e) {
                    System.out.println("Didn't work neck");
                }
                texfieldArray.get(i).setText(info);
            }

            if (i == 3) {
                try {
                    info = bsthe.getWristSize().toString();
                } catch (Exception e) {
                    System.out.println("Didn't work wrist");
                }
                texfieldArray.get(i).setText(info);
            }

            if (i == 4) {
                try {
                    info = bsthe.getChestSize().toString();
                } catch (Exception e) {
                    System.out.println("Didn't work chest");
                }
                texfieldArray.get(i).setText(info);
            }

            if (i == 5) {
                try {
                    info = bsthe.getWaistSize().toString();
                } catch (Exception e) {
                    System.out.println("Didn't work waist");
                }
                texfieldArray.get(i).setText(info);
            }
            if (i == 6) {
                try {
                    info = bsthe.getThighSize().toString();
                } catch (Exception e) {
                    System.out.println("Didn't work thigh");
                }
                texfieldArray.get(i).setText(info);
            }
            if (i == 7) {
                try {
                    info = bsthe.getCalfSize().toString();
                } catch (Exception e) {
                    System.out.println("Didn't work calf");
                }
                texfieldArray.get(i).setText(info);
            }
            else {
                info = "0.0";
            }
        }

        return texfieldArray;
    }

    class DeleteBodyStatListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            SaveBodyWeight deleter = new SaveBodyWeight();
            deleter.deleteSpecific(v.getContext(), bsthe.getDate());

            Context context = v.getContext();
            Intent intent = new Intent(context, BottomNaviClass.class);
            BottomNaviClass.identifier = 4;
            context.startActivity(intent);
            System.out.println("File should get deleted");

        }
    }
    private static Date getDate(long time) {
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();//get your local time zone.
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        sdf.setTimeZone(tz);//set time zone.
        String localTime = sdf.format(new Date(time * 1000));
        Date date = new Date();
        try {
            date = sdf.parse(localTime);//get local date
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}

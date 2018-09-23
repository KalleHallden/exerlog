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
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.graphics.Color.WHITE;

public class BodyStatActivity extends AppCompatActivity {

    Button saveButton;
    Button one;

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

    LinearLayout row;
    LinearLayout topBar;
    LinearLayout totalLayout;
    LinearLayout testLayout;
    LinearLayout layoutTop;
    LinearLayout layoutTop2;
    LinearLayout layoutTop3;
    LinearLayout labelrow;
    LinearLayout volumeRow;
    LinearLayout bar;
    LinearLayout divider;
    LinearLayout textFieldRows;

    LinearLayout.LayoutParams buttonParams;
    LinearLayout.LayoutParams bottomPara;

    GridLayout grid;

    int thirdWidth;
    int width;
    int height;

    View view;

    ArrayList<EditText> textfieldArray;

    public ArrayList<Exercise> savedExercises = new ArrayList<Exercise>();
    ArrayList<Workout> savedWorkouts = new ArrayList<Workout>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();


    }

    public void setUp() {

        view = new View(this);
        SaveWorkout.checkExistingFiles(view.getContext());
        thisWorkout = new Workout();

        savedWorkouts.add(new Workout());
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
        testLayout.setBackgroundColor(MainActivity.grey);
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

        bar = new LinearLayout(this);

        divider = new LinearLayout(this);
        System.out.println("3 worked");
        setLayouts();

    }

    public void setLayouts() {

        topBar.setBackgroundColor(Color.rgb(30, 30, 30));
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
        

        layoutTop.addView(saveButton);


        grid.addView(layoutTop);
        grid.addView(layoutTop2);
        grid.addView(layoutTop3);
        topBar.addView(grid);

        textfieldArray = new ArrayList<EditText>();
        makeTextFields();

        for (int i = 0; i < 8; i++) {
            exerciseRowContainer.addView(textfieldArray.get(i), buttonParams);
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
        saveButton.setTextColor(MainActivity.green);
        saveButton.setBackgroundColor(topBar.getSolidColor());
        saveButton.setOnClickListener(new SaveWorkoutListener());

        System.out.println("5 worked");
        addViews();
    }

    public void completeSetups() {

        SetUp.makeLinearLayoutParamsTopBar(totalLayout);

        BottomNav.makeBottomnavBar(bar, width, view.getContext(), 4);
        System.out.println("7 worked");
        makeContentView();
    }

    public void makeContentView() {

        row.addView(topBar);
        row.addView(exerciseRowContainer);

        row.setLayoutParams(bottomPara);

        totalLayout.addView(row);
        totalLayout.addView(divider);
        totalLayout.addView(bar);
        testLayout.addView(totalLayout, bottomPara);
        setContentView(testLayout);
        System.out.println("8 worked");

    }

    public ArrayList<EditText> makeTextFields() {
        for (int i = 0; i < 8; i++) {
            textfieldArray.add(new EditText(getBaseContext()));
            EditText textfield = textfieldArray.get(i);
            myTextField.makeTextField(textfield, (width / 2));
            textfield.setMinimumWidth(width/2);
            textfield.setMinHeight(100);
            textfield.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textfield.setTextSize(20);

        }

      return textfieldArray;
    }

     class GoBackOnClickListener implements  View.OnClickListener {
                @Override
                 public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, StatDiaryActivity.class);
                    context.startActivity(intent);
                }
        }









    class SaveWorkoutListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            BodyStats bs = new BodyStats();

            for (int i = 0; i < 8; i++) {
                EditText textfield = textfieldArray.get(i);
                String text = String.valueOf(textfield.getText());
                if (i == 0) {
                    try {
                        bs.setBodyWeight(text);
                    } catch (Exception noTextFound) {
                        bs.setBodyWeight(retrievePrevious(i));
                    }
                }
                if (i == 1) {
                   try {
                       bs.setBicepsSize(text);
                   } catch (Exception noTextFound) {
                       bs.setBicepsSize(retrievePrevious(i));
                   }
               } if (i == 2) {
                 try {
                     bs.setNeckSize(text);
                 } catch (Exception noTextFound) {
                     bs.setNeckSize(retrievePrevious(i));
                 }
               }  if (i == 3) {
                   try {
                       bs.setWristSize(text);
                   } catch (Exception noTextFound) {
                       bs.setWristSize(retrievePrevious(i));
                   }
               }  if (i == 4) {
                       try {
                           bs.setChestSize(text);
                       } catch (Exception noTextFound) {
                           bs.setChestSize(retrievePrevious(i));
                       }
               }   if (i == 5) {
                   try {
                       bs.setWaistSize(text);
                   } catch (Exception noTextFound) {
                       bs.setWaistSize(retrievePrevious(i));
                   }
               }  if (i == 6) {
                      try {
                          bs.setThighSize(text);
                      } catch (Exception noTextFound) {
                          bs.setThighSize(retrievePrevious(i));
                      }
               } if (i == 7) {
                        try {
                            bs.setCalfSize(text);
                        } catch (Exception noTextFound) {
                            bs.setCalfSize(retrievePrevious(i));
                        }
                    }

            }


            SaveBodyWeight test = new SaveBodyWeight();
            test.WriteObjectToFile(bs, v.getContext());

            saveButton.setText("Back");
            saveButton.setOnClickListener(new GoBackOnClickListener());

        }
    }


    public String retrievePrevious(int which) {
        String info = "0.0";
        SaveBodyWeight opener = new SaveBodyWeight();

        SaveBodyWeight.checkExistingFiles(getBaseContext());
        BodyStats bs2 = opener.openBodyStat((SaveBodyWeight.n -1), getBaseContext());

        if (which == 0) {
            try {
               info = bs2.getBodyWeight().toString();
            } catch (Exception e) {
                 System.out.println("Didn't work weight");
            }
        }
        if (which == 1) {
            try {
                info = bs2.getBicepsSize().toString();
            } catch (Exception e) {
                System.out.println("Didn't work bicep");
            }
        }

        if (which == 2) {
            try {
                info = bs2.getNeckSize().toString();
            } catch (Exception e) {
                System.out.println("Didn't work neck");
            }
        }

        if (which == 3) {
            try {
                info = bs2.getWristSize().toString();
            } catch (Exception e) {
                System.out.println("Didn't work wrist");
            }
        }

        if (which == 4) {
            try {
                info = bs2.getChestSize().toString();
            } catch (Exception e) {
                System.out.println("Didn't work chest");
            }
        }

        if (which == 5) {
            try {
                info = bs2.getWaistSize().toString();
            } catch (Exception e) {
                System.out.println("Didn't work waist");
            }
        }
        if (which == 6) {
            try {
                info = bs2.getThighSize().toString();
            } catch (Exception e) {
                System.out.println("Didn't work thigh");
            }
        }
        if (which == 7) {
            try {
                info = bs2.getCalfSize().toString();
            } catch (Exception e) {
                System.out.println("Didn't work calf");
            }
        }
        else {
            info = "0.0";
        }

        return info;
    }
}

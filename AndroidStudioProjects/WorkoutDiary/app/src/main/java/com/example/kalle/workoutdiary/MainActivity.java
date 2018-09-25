package com.example.kalle.workoutdiary;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.view.Display;
import android.graphics.Point;
import android.view.View;
import java.util.ArrayList;
import static android.graphics.Color.*;





public class MainActivity extends AppCompatActivity {

    static int green = Color.rgb(4,168,46);
    static int grey = Color.rgb(56,62,66);
    static int red = Color.rgb(176,14,35);
    static int darkgrey = Color.rgb(30,30,30);
    static int black = Color.rgb(0,0,0);

    Button addExerciseButton;
    Button saveButton;
    Button add;
    Button startNewWorkoutButton;

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

    static int numWorkouts;

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
        window.setStatusBarColor(black);

        view = new View(this);
        SaveWorkout.checkExistingFiles(view.getContext());
        thisWorkout = new Workout();
        SaveBodyWeight.checkExistingFiles(view.getContext());

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

        numWorkouts++;
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
        testLayout.setBackgroundColor(grey);
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
        rowList.add(new LinearLayout(this));
        textFieldRows = rowList.get(numberOfExercises);
        layoutTop.addView(saveButton);
        layoutTop3.addView(add);
        
        grid.addView(layoutTop);
        grid.addView(layoutTop2);
        grid.addView(layoutTop3);
        topBar.addView(grid);
        volumeRow.addView(volumeLabel);
        TextFieldMaker.makeTextFields(textFieldRows, view);
        exerciseRowContainer.addView(textFieldRows, buttonParams);

        numberOfExercises = numberOfExercises + 1;

        rowScroller.addView(exerciseRowContainer);
        System.out.println("6 worked");
        completeSetups();

    }

    public void addButtons() {

          saveButton = new Button(this);
          startNewWorkoutButton = new Button(this);
          add = new Button(this);
          add.setOnClickListener(new AddRowOnClickListener());
          add.setText("+");
          add.setTextSize(30);
          add.setTextColor(green);
          add.setBackgroundColor(Color.rgb(30,30,30));
          add.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);

          //saveButton.setBackgroundResource(R.mipmap.ticks);
          saveButton.setText("✓");
          saveButton.setTextSize(30);
          saveButton.setTextColor(green);
          saveButton.setBackgroundColor(topBar.getSolidColor());
          saveButton.setOnClickListener(new SaveWorkoutListener());

          volumeLabel = new TextView(this);
          volumeLabel.setText("Total Volume: 0");
          volumeLabel.setTextColor(darkgrey);
          volumeLabel.setTextSize(20);


          // make save button
          addExerciseButton = new Button(this);
          addExerciseButton.setText("Add");
          addExerciseButton.setOnClickListener(new AddRowOnClickListener());
          System.out.println("5 worked");
          addViews();
    }

    public void completeSetups() {
        int s = 1;
        System.out.println("7 worked " + s);
        s++;


         SetUp.makeLabels(labelrow, view.getContext(), width);
         System.out.println("7 worked " + s);
         s++;

         MyButton.makeButton(addExerciseButton,"green");
         System.out.println("7 worked " + s);
         s++;

         SetUp.makeLinearLayoutParams(volumeRow);
         System.out.println("7 worked " + s);
         s++;

         SetUp.makeLinearLayoutParamsTopBar(totalLayout);
         System.out.println("7 worked " + s);
         s++;


         System.out.println("7 worked " + s);
         s++;

         BottomNav.makeBottomnavBar(bar, width, view.getContext(), 2);
         System.out.println("7 worked");
         makeContentView();
    }

    public void makeContentView() {

         row.addView(topBar);
         row.addView(labelrow);
         row.addView(rowScroller);
         row.addView(addExerciseButton, buttonParams);
         row.addView(volumeRow);

         row.setLayoutParams(bottomPara);

         totalLayout.addView(row);
         totalLayout.addView(divider);
         totalLayout.addView(bar);
         testLayout.addView(totalLayout, bottomPara);
         setContentView(testLayout);
         System.out.println("8 worked");

    }


    class SaveWorkoutListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            SaveWorkout test = new SaveWorkout();
            test.WriteObjectToFile(thisWorkout, v.getContext());
        }
    }

}

class MyLinearLayout {
    public static void setupLayout(LinearLayout linlayout) {
        //linlayout.setBackgroundColor(Color.rgb(0,0,100));
        linlayout.setOrientation(LinearLayout.HORIZONTAL);
        linlayout.setPadding(10, 10, 10, 10);
    }
}

class MyButton {

    public static void makeButton(Button butn, String color) {
        butn.setTextColor(MainActivity.grey);
        butn.setTextSize(20);

        if (color.equals("green")) {
            butn.setBackgroundColor(MainActivity.green);
        } if (color.equals("grey")) {
            butn.setBackgroundColor(Color.rgb(36,42,46));
        } if (color.equals("red")) {
            butn.setBackgroundColor(MainActivity.red);
        }

    }
}


class myTextField {
    public static void makeTextField(EditText texfield, int widths) {
        texfield.setBackgroundColor(MainActivity.green);
        texfield.setTextColor(MainActivity.grey);
        texfield.setTextSize(15);
        texfield.setTypeface(null, Typeface.BOLD);
        texfield.setMaxHeight(100);
        texfield.setMaxLines(3);
        texfield.setMaxWidth(widths);

    }
}
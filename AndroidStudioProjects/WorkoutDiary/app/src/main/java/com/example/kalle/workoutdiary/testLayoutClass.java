package com.example.kalle.workoutdiary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class testLayoutClass {

    static int identify;
    ScrollView rowScroller;
    static ArrayList<LinearLayout> rows;
    int y;
    int width;
    int height;

    View view;

    LinearLayout  rowsInsideScroll;
    LinearLayout container;
    LinearLayout bar;
    LinearLayout scrollContainer;
    LinearLayout.LayoutParams buttonParams;

    public static void removeRow(int index) {
        rows.remove(index);
    }


    public LinearLayout createLayout(View v) {
         container = new LinearLayout(v.getContext());
         container.setBackgroundColor(Color.RED);
         return container;
    }

    public LinearLayout createLayout2(View v) {
        scrollContainer = new LinearLayout(v.getContext());
        scrollContainer.setBackgroundColor(Color.BLUE);
        return scrollContainer;
    }

    public LinearLayout setUp(View v) {


        buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        SetUp.makeButtonAndTextRowParams(buttonParams);

        makeLinears(v);
        makeRows(v);


        LinearLayout.LayoutParams bottomPara = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, Gravity.BOTTOM);

        container.setLayoutParams(bottomPara);

        rowScroller.addView(rowsInsideScroll);
        scrollContainer.addView(rowScroller);
        scrollContainer.setLayoutParams(bottomPara);
        container.addView(scrollContainer);
        container.addView(bar);
        container.setBackgroundColor(MainActivity.grey);
        return container;
    }


    public void makeLinears(View v) {
        rows = new ArrayList<LinearLayout>();
        rowsInsideScroll.setOrientation(LinearLayout.VERTICAL);

        rowScroller.setLayoutParams(new RelativeLayout.LayoutParams(
                ScrollView.LayoutParams.FILL_PARENT, height - 300));
        rowScroller.setBackgroundColor(MainActivity.grey);

        container.setOrientation(LinearLayout.VERTICAL);
        BottomNav.makeBottomnavBar(bar, width, view.getContext(), 3);
    }


    public void makeRows(View v) {
        for (int i = 0; i < ((SaveWorkout.n) + 1); i++) {
            rows.add(new LinearLayout(v.getContext()));
            LinearLayout rowCreated = rows.get(i);
            String theId = Integer.toString(i);
            rowCreated.setOnClickListener(new RowClickedOnClickListener(theId));
        }
        setUpRows(v);
    }

    public void setUpRows(View v) {
        int counter = SaveWorkout.n;

        while (counter != 0) {
            System.out.println("Row counter is at " + counter);

            LinearLayout rowCreated = rows.get(counter - 1);
            rowCreated.setBackgroundColor(MainActivity.green);
            rowCreated.setMinimumWidth(1000);
            rowCreated.setMinimumHeight(80);
            TextView name = new TextView(v.getContext());
            name.setText("Workout " + counter);
            name.setTypeface(null, Typeface.BOLD);
            name.setTextSize(15);
            name.setTextColor(MainActivity.grey);
            name.setPadding(20, 40, 0, 0);

            rowCreated.addView(name, buttonParams);

            rowsInsideScroll.addView(rowCreated, buttonParams);
            counter = counter -1;
        }
    }


    class RowClickedOnClickListener implements View.OnClickListener {
        String id;

        public RowClickedOnClickListener(String name) {
            id = name;
        }

        public RowClickedOnClickListener() {
        }

        @Override
        public void onClick(View view) {

            System.out.println("Row " + id + " clicked");
            Context context = view.getContext();
            System.out.println("add");
            identify = Integer.parseInt(id);
            System.out.println("two");
            Intent i = new Intent(context, CopyOfWorkoutActivity.class);
            System.out.println("three");
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            System.out.println("four");
            //Intent intent = new Intent(context, BottomNavigation.class);
            context.startActivity(i);
            System.out.println("five");
        }


    }

}


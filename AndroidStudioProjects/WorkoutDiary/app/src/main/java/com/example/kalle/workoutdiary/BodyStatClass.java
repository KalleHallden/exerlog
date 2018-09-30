package com.example.kalle.workoutdiary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class BodyStatClass {

    static int identify;
    int y;
    int width;
    int height;

    View view;

    LinearLayout container;
    static ArrayList<LinearLayout> rowList;

    public static void removeRow(int index) {
        rowList.remove(index);
    }

    public static LinearLayout createLayout(View v, LinearLayout containers) {
        make(containers, v);
        return containers;
    }

    public static void make(LinearLayout containers, View v) {
        containers.setBackgroundColor(BottomNaviClass.grey);
        containers.setOrientation(LinearLayout.VERTICAL);
        LinearLayout rowsInsideScroll = new LinearLayout(v.getContext());
        LinearLayout scrollContainer = new LinearLayout(v.getContext());

        Button addEntryButton = new Button(v.getContext());
        MyButton.makeButton(addEntryButton, "red");
        addEntryButton.setMinimumWidth(BottomNaviClass.width);
        addEntryButton.setMinimumHeight(100);
        addEntryButton.setText("Add entry");
        addEntryButton.setOnClickListener(new AddEntryListener());


        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        SetUp.makeButtonAndTextRowParams(buttonParams);

        rowList = new ArrayList<LinearLayout>();


        ScrollView rowScroller = new ScrollView(v.getContext());
        rowLayout(rowScroller);
        rowScroller.addView(rowsInsideScroll);

        makeRows(v, rowList);
        setUpRows(v, rowsInsideScroll, rowList, buttonParams);





        rowsInsideScroll.setOrientation(LinearLayout.VERTICAL);


        scrollContainer.addView(rowScroller);
        containers.addView(addEntryButton);
        containers.addView(scrollContainer);
    }

    public static void rowLayout(ScrollView scrollviewRows) {
        scrollviewRows.setLayoutParams(new RelativeLayout.LayoutParams(
                ScrollView.LayoutParams.FILL_PARENT, BottomNaviClass.height - 300));
    }


    public static void makeRows(View v, ArrayList<LinearLayout> rowList) {
        for (int i = 0; i < ((SaveBodyWeight.n) + 1); i++) {

            rowList.add(new LinearLayout(v.getContext()));
            LinearLayout rowCreated = rowList.get(i);
            String theId = Integer.toString(i);
            rowCreated.setOnClickListener(new RowClickedOnClickListener(theId));
        }
    }

    public static void setUpRows(View v, LinearLayout rowsInsideScroll, ArrayList<LinearLayout> rowList, LinearLayout.LayoutParams buttonP) {

        int counter = SaveBodyWeight.n;

        while (counter != 0) {
            System.out.println("Row counter is at " + counter);

            LinearLayout rowCreated = rowList.get(counter - 1);
            rowCreated.setBackgroundColor(BottomNaviClass.black);
            rowCreated.setMinimumWidth(1000);
            rowCreated.setMinimumHeight(80);
            TextView name = new TextView(v.getContext());
            name.setText("Body Stat " + counter);
            name.setTypeface(null, Typeface.BOLD);
            name.setTextSize(15);
            name.setTextColor(BottomNaviClass.red);
            name.setPadding(20, 40, 0, 0);

            rowCreated.addView(name, buttonP);

            rowsInsideScroll.addView(rowCreated, buttonP);
            counter = counter -1;
        }
    }

    static class AddEntryListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), BodyStatActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            v.getContext().startActivity(i);
            BodyStatActivity.newEntryClicked = true;
        }
    }

    static class RowClickedOnClickListener implements View.OnClickListener {
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
            Intent i = new Intent(context, BodyStatActivity.class);
            System.out.println("three");
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            System.out.println("four");
            context.startActivity(i);
            System.out.println("five");
            BodyStatActivity.newEntryClicked = false;
        }


    }

}
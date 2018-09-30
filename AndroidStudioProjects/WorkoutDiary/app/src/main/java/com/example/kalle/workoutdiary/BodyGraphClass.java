package com.example.kalle.workoutdiary;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

public class BodyGraphClass {
    static int identify;
    int y;
    int width;
    int height;

    static int[] actualHeight;
    static int initialHeight;
    static BodyGraphClass.Ani a;

    View view;

    LinearLayout container;
    static LinearLayout rowsInsideScroll;

    static ArrayList<LinearLayout> rowList;
    static ArrayList<BodyStats> statList;

    static LinearLayout.LayoutParams bottomPara;
    static LinearLayout.LayoutParams buttonParams;

    public static void removeRow(int index) {
        rowList.remove(index);
    }

    public static LinearLayout createLayout(View v, LinearLayout containers) {
        make(containers, v);
        return containers;
    }

    public static void make(LinearLayout containers, View v) {

        statList = new ArrayList<BodyStats>();
        buildit(v);
        containers.setBackgroundColor(BottomNaviClass.grey);
        containers.setOrientation(LinearLayout.VERTICAL);
        rowsInsideScroll = new LinearLayout(v.getContext());
        LinearLayout scrollContainer = new LinearLayout(v.getContext());
        LinearLayout allViews = new LinearLayout(v.getContext());
        allViews.setOrientation(LinearLayout.VERTICAL);

        LinearLayout divider = new LinearLayout(v.getContext());

        Button addEntryButton = new Button(v.getContext());
        MyButton.makeButton(addEntryButton, "red");
        addEntryButton.setMinimumWidth(BottomNaviClass.width);
        addEntryButton.setMinimumHeight(100);
        addEntryButton.setText("Day view");


        buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        makeButtonAndTextRowParams(buttonParams);

        bottomPara = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, Gravity.BOTTOM);

        rowList = new ArrayList<LinearLayout>();


        HorizontalScrollView rowScroller = new HorizontalScrollView(v.getContext());
        rowScroller.setLayoutParams(new RelativeLayout.LayoutParams(
                ScrollView.LayoutParams.FILL_PARENT, BottomNaviClass.height));

        makeRows(v, rowList);
        setRowHeights(v, rowsInsideScroll);


        rowsInsideScroll.setOrientation(LinearLayout.HORIZONTAL);

        allViews.addView(addEntryButton);
        rowScroller.addView(rowsInsideScroll);
        allViews.addView(rowScroller, bottomPara);
        allViews.addView(divider);
        containers.addView(allViews, bottomPara);


    }

    public static void makeButtonAndTextRowParams(LinearLayout.LayoutParams params) {
        params.leftMargin = 10;
        params.rightMargin = 10;
        params.topMargin = 20;
        params.bottomMargin = 20;
        params.gravity = Gravity.BOTTOM;
    }


    public static void makeRows(View v, ArrayList<LinearLayout> rowList) {
        for (int i = 0; i < statList.size(); i++) {
            rowList.add(new LinearLayout(v.getContext()));
            LinearLayout rowCreated = rowList.get(i);
            String theId = Integer.toString(i);
            rowCreated.setOnClickListener(new StatChooserClass.RowClickedOnClickListener(theId));
        }
    }

    public static void setUpRows(View v, LinearLayout rowsInsideScroll, ArrayList<LinearLayout> rowList, LinearLayout.LayoutParams buttonP) {

        int counter = statList.size();

        while (counter != 0) {
            System.out.println("Row counter is at " + counter);

            LinearLayout rowCreated = rowList.get(counter - 1);
            rowCreated.setBackgroundColor(BottomNaviClass.red);
            rowCreated.setMinimumWidth(50);
            rowCreated.setMinimumHeight(300);

            rowsInsideScroll.addView(rowCreated, buttonP);

            counter = counter -1;
        }
    }

    public static void setRowHeights(View v, LinearLayout rowsInsideScroll) {

        int counter = statList.size();

        int[] s = new int[counter];
        actualHeight = new int[counter];
        while (counter != 0) {

            System.out.println("This is the weght: " + SaveBodyWeight.bodyweightList[counter-1]);
            Double num = getMax();

            Double y = SaveBodyWeight.bodyweightList[counter-1];
            Double c = 10.0;
            Double f = findMaxHeight();
            Double height;
            int x = 0;

            if (num > 10) {
                c =  y / num;
                x = c.intValue();
            } else {
                while (f * c < BottomNaviClass.height -100 ) {
                    c = c * 2;
                    System.out.println(c);
                }
                x = (int) (y * c);
            }
            System.out.println("This " +x);


            System.out.println("Row counter is at " + counter);
            BodyStats work = statList.get(counter - 1);
            LinearLayout rowCreated = rowList.get(counter - 1);
            LinearLayout rowForRow = new LinearLayout(v.getContext());
            rowCreated.setBackgroundColor(BottomNaviClass.red);
            rowCreated.setMinimumWidth(50);

            System.out.println("This is the height: " + x);
            s[counter-1] = (SaveBodyWeight.bodyweightList[counter-1]).intValue();

            rowCreated.setMinimumHeight(x);
            rowForRow.addView(rowCreated);

            //a = new Ani();


            actualHeight[counter-1] = x;
            a = new Ani(rowCreated, counter-1);
            System.out.println("Hi: " + 1);
            a.setDuration(800);
            rowCreated.startAnimation(a);



            rowsInsideScroll.addView(rowForRow, buttonParams);

            counter = counter -1;
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
            System.out.println("five");
        }


    }






    public static void buildit(View v) {
        SaveBodyWeight opener = new SaveBodyWeight();

        for (int i = 0; i < SaveBodyWeight.n; i++) {
            statList.add(new BodyStats());
            BodyStats workout = statList.get(i);
            try {
                workout = opener.openBodyStat(i, v.getContext());
                System.out.println("BodyStat built: " + i);
            } catch (Exception e) {
                System.out.println("No workout found");
                System.out.println(statList.size());
            }
        }
    }

    static class Ani extends Animation {
        LinearLayout row;
        int id;
        public Ani(LinearLayout rows, int identity) {
            row = rows;
            id = identity;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            int newHeight;

            newHeight = (int)(actualHeight[id] * interpolatedTime);
            row.removeAllViews();
            row.getLayoutParams().height = newHeight;
            row.requestLayout();

        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            initialHeight = actualHeight[id];
            System.out.println("Heightish: " + initialHeight);
        }

        @Override
        public boolean willChangeBounds() {
            return true;
        }
    }

    public static Double getMax() {

        Double maxHeight = 0.0;
        Double returnMultiplicationNumber = 0.0;
        for (int i = 0; i < statList.size(); i++ ) {
            if (maxHeight < SaveBodyWeight.bodyweightList[i]) {
                maxHeight = SaveBodyWeight.bodyweightList[i];
                System.out.println("here " + maxHeight);
            }
        }
        if (maxHeight > BottomNaviClass.height - 100) {
            while (maxHeight / returnMultiplicationNumber > BottomNaviClass.height - 100) {
                returnMultiplicationNumber += 10;
            }
        } else {
            returnMultiplicationNumber = 10.0;
        }
        return returnMultiplicationNumber;
    }

    public static Double findMaxHeight() {
        Double maxHeight = 0.0;
        for (int i = 0; i < statList.size(); i++ ) {
            if (maxHeight < SaveBodyWeight.bodyweightList[i]) {
                maxHeight = SaveBodyWeight.bodyweightList[i];
                System.out.println("here " + maxHeight);
            }
        }
        return maxHeight;

    }

}
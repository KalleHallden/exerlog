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
import android.widget.LinearLayout;

public class BottomNaviClass extends AppCompatActivity {


    public static int height;
    public static int width;
    public static View view;
    LinearLayout bar;
    LinearLayout viewContainer;
    LinearLayout pageHolder;
    LinearLayout page;

    LinearLayout.LayoutParams bottomPara;

    public static int id;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
    }

    public void setUp() {
        System.out.println("This is current id: " + id);
        bar = new LinearLayout(this);
        viewContainer = new LinearLayout(this);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        view = new View(this);
        pageHolder = new LinearLayout(this);



        bottomPara = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, Gravity.BOTTOM);
        viewContainer.setLayoutParams(bottomPara);
        viewContainer.setOrientation(LinearLayout.VERTICAL);
        makeNav();

        pageHolder.setLayoutParams(bottomPara);
        viewContainer.addView(pageHolder);
        viewContainer.addView(bar);
        setContentView(viewContainer);

    }

    public void makeNav() {
        BottomNav.makeBottomnavBar(bar, width, view.getContext(), 3);
        BottomNav.stats.setOnClickListener(new NavBarOnClickListener1(0));
        BottomNav.profile.setOnClickListener(new NavBarOnClickListener1(1));
        BottomNav.addWorkout.setOnClickListener(new NavBarOnClickListener1(2));
        BottomNav.diary.setOnClickListener(new NavBarOnClickListener1(3));
        BottomNav.bodyStats.setOnClickListener(new NavBarOnClickListener1(4));
    }

    public void createSpecific(int identifier) {
        if (identifier == 0) {
            pageHolder.removeAllViews();
            makeStatChooser();
            pageHolder.addView(page);
        }
        if (identifier == 1) {
            pageHolder.removeAllViews();
            page = new LinearLayout(this);
            page.setBackgroundColor(Color.GREEN);
            page.setLayoutParams(bottomPara);
            pageHolder.addView(page);
        }
        if (identifier == 2) {
            pageHolder.removeAllViews();
            makeDiary();
            pageHolder.addView(page);
        }
        if (identifier == 3) {
            pageHolder.removeAllViews();
            page = new LinearLayout(this);
            page.setBackgroundColor(Color.BLUE);
            page.setLayoutParams(bottomPara);
            pageHolder.addView(page);
        }
        if (identifier == 4) {
            pageHolder.removeAllViews();
            page = new LinearLayout(this);
            page.setBackgroundColor(Color.WHITE);
            page.setLayoutParams(bottomPara);
            pageHolder.addView(page);
        }
    }

    public void makeDiary() {
        testLayoutClass tryit = new testLayoutClass();
        page = tryit.createLayout(view);
        page.setLayoutParams(bottomPara);
    }

    public void makeStatChooser() {
        testLayoutClass tryit = new testLayoutClass();
        page = tryit.createLayout2(view);
        page.setLayoutParams(bottomPara);
    }


    class NavBarOnClickListener1 implements View.OnClickListener {

        int id;

        public NavBarOnClickListener1(int name) {
            id = name;
        }

        public NavBarOnClickListener1() {
        }

        @Override
        public void onClick(View v) {
            System.out.println("Row " + id + " clicked");
            Context context = v.getContext();
            if (id == 1) {
                BottomNaviClass.id = id;
                createSpecific(id);
            } else if (id == 2) {
                BottomNaviClass.id = id;
                createSpecific(id);
            } else if (id == 3) {
                BottomNaviClass.id = id;
                createSpecific(id);
            } else if (id == 4) {
                BottomNaviClass.id = id;
                createSpecific(id);
            } else if (id == 5) {
                BottomNaviClass.id = id;
                createSpecific(id);
            }

        }
    }
}

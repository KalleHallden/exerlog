package com.example.kalle.workoutdiary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

public class BodyGraphActivity extends AppCompatActivity {
    static int identify;
    static ArrayList<LinearLayout> rows;
    int width;
    int height;

    View view;

    ScrollView scrollView;

    LinearLayout container;
    LinearLayout buttonRows;
    LinearLayout bar;
    LinearLayout.LayoutParams buttonParams;

    Button workoutStatButton;
    Button bodyStatButton;

    DrawView drawView;
    ArrayList<DrawView> listOfDraw = new ArrayList<DrawView>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();

    }

    public void setUp() {



        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(MainActivity.black);


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        view = new View(this);

        SaveBodyWeight.checkExistingFiles(view.getContext());


        buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        SetUp.makeButtonAndTextRowParams(buttonParams);

        makeLinears();
        int x = 80;

        LinearLayout.LayoutParams bottomPara = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, Gravity.BOTTOM);

        PointF pointax = new PointF(20, 0);
        DrawView draw = new DrawView(this);

        for (int i = 0; i < 2; i ++) {
             //listOfDraw.add(new DrawView(this));
             //DrawView draw = listOfDraw.get(i);
             draw.setPointA(pointax);
             draw.setBackgroundColor(MainActivity.grey);
             SaveBodyWeight opener = new SaveBodyWeight();
             BodyStats weightStat = new BodyStats();
             opener.openBodyStat(i, view.getContext());
             x = weightStat.getBodyWeight().intValue();
             PointF pointay = new PointF(height- ((x*10) +220), height-100);
             draw.setPointB(pointay);
             draw.draw();
             pointax = pointay;
        }



        PointF pointbx = new PointF();
        PointF pointby = new PointF();




        container.addView(draw, bottomPara);
        container.addView(bar);
        container.setBackgroundColor(MainActivity.grey);
        setContentView(container, bottomPara);


    }


    public void makeLinears() {

        buttonRows = new LinearLayout(getBaseContext());
        buttonRows.setLayoutParams(buttonParams);
        buttonRows.setOrientation(LinearLayout.VERTICAL);

        container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);

        scrollView = new ScrollView(getBaseContext());
        scrollView.setBackgroundColor(MainActivity.green);

        bar = new LinearLayout(this);
        BottomNav.makeBottomnavBar(bar, width, view.getContext(), 1);
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

            System.out.println("two");
            Intent i = null;

            if (id.equals("1")) {
                i = new Intent(context, StatsActivity.class);
                System.out.println("three");
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                System.out.println("four");
            }
            if (id.equals("2")) {
                i = new Intent(context, BodyGraphActivity.class);
                System.out.println("three");
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                System.out.println("four");
            }

            context.startActivity(i);


            System.out.println("five");
        }


    }

    class DrawView extends View {
        private Paint paint = new Paint();
        private PointF pointA, pointB;


        public DrawView(Context context) {
            super(context);
        }

        public DrawView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public DrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            paint.setColor(MainActivity.green);
            paint.setStrokeWidth(7);
            canvas.drawLine(pointA.x, pointB.x, pointA.y, pointB.y, paint);
            super.onDraw(canvas);
        }

        public void setPointA(PointF point) {
            pointA = point;
        }

        public void setPointB(PointF point) {
            pointB = point;
        }

        public void draw() {
            invalidate();
            requestLayout();
        }

    }

}


